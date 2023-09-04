package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.*;
import com.bancolombia.prubea.entity.*;
import com.bancolombia.prubea.repository.EncuestaRepository;
import com.bancolombia.prubea.repository.PreguntaERepository;
import com.bancolombia.prubea.repository.RespuestaRepository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EncuestaServiceImpl implements IEncuestaService {

    private final EncuestaRepository surveyRepository;
    private final PreguntaERepository questionRepository;
    private final RespuestaRepository answerRespository;

    public EncuestaServiceImpl(EncuestaRepository surveyRepository,
                               PreguntaERepository questionRepository, RespuestaRepository answerRespository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.answerRespository = answerRespository;
    }

    public ServiceResponseDto createSurvey(EncuestaDto surveyDto){
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
                PreguntaE question = questionEDto.convertQuestionSDtoToQuestionS(questionEDto, survey, questionType);

                questionRepository.save(question);

                Respuesta answer = RespuestaDto.convertAnswerDtoToAnswer(questionEDto.getAnswerDto(), question);

                answerRespository.save(answer);

            });
            // se guardan las preguntas con la relacion Encuesta/Pregunta
//            questionRepository.saveAll(questions);
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
    public ServiceResponseDto listSurvey() {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try {
            if(surveyRepository.count() != 0){
                List<Encuesta> surveys = surveyRepository.findAll();
                List<EncuestaDto> surveysDto = surveys.stream()
                        .map(EncuestaDto::convertSurveyToSurveyDto)
                        .collect(Collectors.toList());
                data.put("objects", surveysDto);
                data.put("total", surveyRepository.count());
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
    public ServiceResponseDto listFullSurvey(String idSurvey) {
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
                        .map(PreguntaEDto::convertQuestionSToQuestionSDto).collect(Collectors.toSet()));
                surveyDto.setQuestionDto(surveyDto.getQuestionDto());
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
    public ServiceResponseDto findSurveyById(String idEncuesta) {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try {
            if(surveyRepository.existsById(idEncuesta)){
                data.put("EncuestaDto", EncuestaDto
                        .convertSurveyToSurveyDto(surveyRepository.getEncuestaForId(idEncuesta)));
            }else{
                statusCode = Constants.NOT_FOUND_STATUS_CODE;
                data.put("message", "La encuesta buscad no se encontro");
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            log.error("findSurveyById failed", e);
        }
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto updateSurvey(EncuestaDto encuestaDto) {
        return null;
    }

    @Override
    public ServiceResponseDto deleteSurvey(String idEncuesta) {
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
