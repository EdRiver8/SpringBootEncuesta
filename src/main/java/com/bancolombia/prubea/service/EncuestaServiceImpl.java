package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.*;
import com.bancolombia.prubea.entity.*;
import com.bancolombia.prubea.repository.EncuestaRepository;
import com.bancolombia.prubea.repository.PersonaEncuestaRespository;
import com.bancolombia.prubea.repository.PreguntaERepository;
import com.bancolombia.prubea.repository.RespuestaRepository;
import com.bancolombia.prubea.util.Constants;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EncuestaServiceImpl implements IEncuestaService {

    private final EncuestaRepository surveyRepository;
    private final PreguntaERepository questionRepository;
    private final RespuestaRepository answerRespository;
    private final PersonaEncuestaRespository personaEncuestaRespository;

    public EncuestaServiceImpl(EncuestaRepository surveyRepository,
                               PreguntaERepository questionRepository, RespuestaRepository answerRespository, PersonaEncuestaRespository personaEncuestaRespository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.answerRespository = answerRespository;
        this.personaEncuestaRespository = personaEncuestaRespository;
    }

    @Override
    public ServiceResponseDto validateActiveSatisfactionSurvey(String idSurveyType){
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        try{
            if(idSurveyType.equals("1")){
                data.put("message", "Puedes crear la encuesta personalizada!");
            }else{
                if(surveyRepository.count() == 0){
                    data.put("message", "No existen encuestas aun!, puedes crear la 1ra.");
                }else{
                    if(surveyRepository.findByTipoEncuestaEsEncuesta("2", "Activada").isPresent()){
                        Encuesta survey = surveyRepository
                                .findByTipoEncuestaEsEncuesta("2", "Activada").orElse(null);

                        EncuestaDto surveyDto = EncuestaDto.convertSurveyToSurveyDto(survey);
                        surveyDto.setQuestionDto(survey.getPreguntas().stream()
                                .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithoutAnswers).collect(Collectors.toSet()));

                        statusCode = Constants.CONFLICT_WITH_CURRENT_STATE;
                        data.put("objects", surveyDto);
                        data.put("total", 1);
                        data.put("message", "Ya hay una encuesta Activa de satisfaccion");
                        data.put("Active", true);
                    }
                    else{
                        data.put("message", "No existe encuesta de satisfaccion activa, puedes crear la encuesta!");
                        data.put("Active", false);
                    }
                }
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al buscar una encuesta de satisfaccion activa!");
            log.error("validateActiveSatisfactionSurvey failed", e);
        }
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }

    public String validateConditionsToCreateSurvey(EncuestaDto surveyDto){
        String validate = "";
        if(surveyDto.getQuestionDto().isEmpty()){
            validate = "La encuesta no se puede guardar, ya que no tiene preguntas asociadas";
        }else if(surveyDto.getSurveyTypeDto().getIdSurveyType().equals("1")){
            validate = "Puedes crear la encuesta";
        }else if(surveyRepository.findByTipoEncuestaEsEncuesta("2", "Activada").isPresent()){
            validate = "Ya hay una encuesta de satisfaccion activada!";
        }
        return validate;
    }

    @Override
    public ServiceResponseDto createSurvey(EncuestaDto surveyDto){
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        try{
            if(surveyDto.getSurveyTypeDto().getIdSurveyType().equals("1")){
                create(surveyDto);
                data.put("message", "encuesta creada con exito!");
            }else{
                if(surveyRepository.count() > 0){
                    if(!surveyRepository.findByTipoEncuestaEsEncuesta("2", "Activada").isPresent()
                            && !surveyDto.getQuestionDto().isEmpty()){
                        create(surveyDto);
                        data.put("message", "encuesta creada con exito!");
                    }else{
                        Encuesta survey = surveyRepository
                                .findByTipoEncuestaEsEncuesta("2", "Activada").orElse(null);
                        EncuestaDto surveyDtoFind = EncuestaDto.convertSurveyToSurveyDto(survey);
                        surveyDto.setQuestionDto(survey.getPreguntas().stream()
                                .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithoutAnswers).collect(Collectors.toSet()));
                        statusCode = Constants.CONFLICT_WITH_CURRENT_STATE;
                        data.put("objects", surveyDtoFind);
                        data.put("total", 1);
                        data.put("message", "Ya hay una encuesta activa!");
                    }
                }else{
                    create(surveyDto);
                    data.put("message", "1ra encuesta de la DB creada con exito!");
                }
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al tratar de crear la encuesta!");
            log.error("createSurvey failed", e);
        }
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);

        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto createSurveyWithAnswers(EncuestaDto surveyDto){
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        try{
            // Validar el TipoEncuesta que se enviara a Encuesta
            TipoEncuesta surveyType = TipoEncuestaDto
                    .convertSurveyTypeDtoToSurveyType(surveyDto.getSurveyTypeDto());
            // Se envia EncuestaDto y TipoEncuesta al EncuestaDto
            Encuesta survey = surveyDto.convertSurveyDtoToSurvey(surveyDto, surveyType);
            // Se guarda entidad Encuesta que recibe fk TipoEncuesta y envia Fk a Pregunta
            surveyRepository.save(survey);
            // Se crean preguntas se envia encuesta del paso anterior y se asigna TipoPregunta
            surveyDto.getQuestionDto().stream().forEach(questionEDto -> {
                // Validar TipoPregunta, para ser enviado a la Pregunta
                TipoPregunta questionType = TipoPreguntaDto.convertQuestionTypeDtoToQuestionType
                        (questionEDto.getQuestionTypeDto());

                // Enviar datos necesarios para crear la pregunta desde su DTO y Retornarla
                PreguntaE question = questionEDto.convertQuestionsDtoToQuestions(questionEDto, survey, questionType);
                questionRepository.save(question); // salvar la pregunta antes de la respuesta asociada

                // al crear o responder solo envia una respuesta por usuario, por eso solo se obtiene el 1ro
                Set<Respuesta> answers = questionEDto.getAnswerDto().stream().map(respuestaDto -> {
                    return RespuestaDto.convertAnswerDtoToAnswer(respuestaDto, question);
                }).collect(Collectors.toSet());
                answerRespository.saveAll(answers);

            });
            data.put("message", "encuesta creada con exito!");
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al tratar de crear la encuesta!");
            log.error("createSurvey failed", e);
        }
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);

        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto listSurveyWithoutQuestions(int page, int size, String sortBy) {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try {
            if(surveyRepository.count() != 0){
                List<Encuesta> surveys = surveyRepository.findAll();
                List<EncuestaDto> surveysDto = surveys.stream().map(EncuestaDto::convertSurveyToSurveyDto)
                        .collect(Collectors.toList());
                Page<EncuestaDto> result = convertirListaAConsultaPaginada(surveysDto, page, size);

                data.put("objects", result.getContent());
                data.put("Size", result.getSize());
                data.put("Pages", result.getTotalPages());
                data.put("total", result.getTotalElements());
            }else{
                statusCode = Constants.NOT_FOUND_STATUS_CODE;
                data.put("message","No se encuentran encuestas para listar!");
                data.put("total", surveyRepository.count());
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al listar las encuestas");
            log.error("listSurvey failed", e);
        }
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto getSurveyWithQuestions(String idSurvey) {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try {
            if(surveyRepository.count() != 0 && surveyRepository.getEncuestaForId(idSurvey) != null){
                Encuesta survey = surveyRepository.getEncuestaForId(idSurvey);
//                Encuesta survey = surveyRepository.findById(idSurvey).orElseThrow(() -> ExceptionPersonalizada);
                EncuestaDto surveyDto = EncuestaDto.convertSurveyToSurveyDto(survey);
//                surveyDto.setSurveyTypeDto(TipoEncuestaDto.convertSurveyTypeToSurveyTypeDto(survey.getTipoEncuesta()));
                surveyDto.setQuestionDto(survey.getPreguntas().stream()
                        .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithoutAnswers).collect(Collectors.toSet()));

                data.put("objects", surveyDto);
                data.put("total", 1);
            }else{
                statusCode = Constants.NOT_FOUND_STATUS_CODE;
                data.put("message","No se encuentra la encuesta!");
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al listar las encuestas");
            log.error("listSurvey failed", e);
        }
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto getSurveyWithQuestionsAndAnswers(String idSurvey) {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try {
            if(surveyRepository.count() != 0 && surveyRepository.existsById(idSurvey)){

                Encuesta survey = surveyRepository.getEncuestaForId(idSurvey);
                EncuestaDto surveyDto = EncuestaDto.convertSurveyToSurveyDto(survey);

                // verificar si al menos una pregunta tiene al menos una respuesta
                boolean isSurveyWithAnswers = survey.getPreguntas().stream()
                        .anyMatch(preguntaE -> !preguntaE.getRespuestas().isEmpty());
                if (isSurveyWithAnswers) {
                    // Por lo menos una pregunta con respuesta (no deberia suceder, o todas tienen o ninguna tiene)
                    surveyDto.setQuestionDto(survey.getPreguntas().stream()
                            .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithAnswers)
                            .collect(Collectors.toSet()));
                } else {
                    surveyDto.setQuestionDto(survey.getPreguntas().stream()
                            .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithoutAnswers)
                            .collect(Collectors.toSet()));
                    statusCode = Constants.NO_CONTENT_STATUS_CODE;
                    data.put("message","La encuesta existe, pero no tiene respuestas asociadas!");
                }
                data.put("objects", surveyDto);
                data.put("total", 1);
            }else{
                statusCode = Constants.NOT_FOUND_STATUS_CODE;
                data.put("message","No se encuentra la encuesta!");
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al listar la encuesta");
            log.error("listSurvey failed", e);
        }
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto deleteSurvey(String idEncuesta) {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try{
            if(surveyRepository.existsById(idEncuesta)){
                Encuesta survey = surveyRepository.getEncuestaForId(idEncuesta);
                Set<PreguntaE> preguntas = survey.getPreguntas();
                // verificar si al menos una pregunta tiene al menos una respuesta
                boolean isSurveyWithAnswers = survey.getPreguntas().stream().anyMatch(preguntaE -> !preguntaE.getRespuestas().isEmpty());
                //Verifica si la encuesta ya esta asignada a alguien dentro de la tabla PersonaEncuesta
                List<PersonaEncuesta> personAssign = personaEncuestaRespository.findSurveyPersonByIdSurvey(idEncuesta);
                if (!isSurveyWithAnswers && personAssign.isEmpty()) {
                    questionRepository.deleteAll(preguntas);
                    surveyRepository.deleteById(idEncuesta);
                    data.put("message", "La encuesta se ha borrado");
                } else {
                    //Se encuentran respuestas o ya hay alguna asignacion en PersonaEncuesta
                    survey.setEsEncuesta("Desactivada");
                    surveyRepository.save(survey);
                    data.put("message", "La encuesta se ha desactivado");
                }
            }else{
                statusCode = Constants.NOT_FOUND_STATUS_CODE;
                data.put("message", "La encuesta buscada no se encontro");
            }
        }catch(Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            log.error("deleteSurvey failed", e);
        }
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }




    public void create(EncuestaDto surveyDto){
        // Validar el TipoEncuesta que se enviara a Encuesta
        TipoEncuesta surveyType = TipoEncuestaDto
                .convertSurveyTypeDtoToSurveyType(surveyDto.getSurveyTypeDto());
        // Se envia EncuestaDto y TipoEncuesta al EncuestaDto
        Encuesta survey = surveyDto.convertSurveyDtoToSurvey(surveyDto, surveyType);
        // Se guarda entidad Encuesta que recibe fk TipoEncuesta y envia Fk a Pregunta
        surveyRepository.save(survey);
        // Se crean preguntas se envia encuesta del paso anterior y se asigna TipoPregunta
        surveyDto.getQuestionDto().stream().forEach(questionEDto -> {
            // Validar TipoPregunta, para ser enviado a la Pregunta
            TipoPregunta questionType = TipoPreguntaDto.convertQuestionTypeDtoToQuestionType
                    (questionEDto.getQuestionTypeDto());

            // Enviar datos necesarios para crear la pregunta desde su DTO y Retornarla
            PreguntaE question = questionEDto.convertQuestionsDtoToQuestions(questionEDto, survey, questionType);
            questionRepository.save(question);
        });
    }


    public Page<EncuestaDto> convertirListaAConsultaPaginada(List<EncuestaDto> listaDto, int pageNumber, int pageSize) {
        int elementoInicio = (pageNumber == 1) ? 0 : (pageNumber - 1) * pageSize;
        int elementoFin = Math.min(elementoInicio + pageSize, listaDto.size());

        List<EncuestaDto> subListaDto = listaDto.subList(elementoInicio, elementoFin);

        return new PageImpl<>(subListaDto, PageRequest.of(pageNumber-1, pageSize), listaDto.size());
    }

    @Override
    public byte[] generarReporteCsvEncuesta(String surveyId) throws IOException {
        Encuesta encuesta = surveyRepository.getEncuestaForId(surveyId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream))) {
            // Escribir encabezados del CSV
            String[] encabezados = {"ID de Pregunta", "Pregunta", "ID de Respuesta", "Respuesta", "ID de Persona"};
            csvWriter.writeNext(encabezados);
            // Iterar a través de las preguntas y respuestas de la encuesta
            for (PreguntaE pregunta : encuesta.getPreguntas()) {
                for (Respuesta respuesta : pregunta.getRespuestas()) {
                    String[] fila = {
                            String.valueOf(pregunta.getIdPregunta()),
                            pregunta.getDsPregunta(),
                            String.valueOf(respuesta.getIdRespuesta()),
                            respuesta.getRespuesta(),
                            String.valueOf(respuesta.getPersonaEncuesta().getIdPersonaEncuesta())
                    };
                    csvWriter.writeNext(fila);
                }
            }
        }
        return outputStream.toByteArray();
    }


    @Override
    public ServiceResponseDto updateSurvey(EncuestaDto encuestaDto) {
        return null;
    }

    @Override
    public ServiceResponseDto findSurveyById(String idEncuesta) {
        return null;
    }

    @Override
    public ServiceResponseDto activateSurvey(String idEncuesta) {
        return null;
    }

    @Override
    public ServiceResponseDto deactivateSurvey(String idEncuesta) {
        return null;
    }

}
