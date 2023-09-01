package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.*;
import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.entity.TipoEncuesta;
import com.bancolombia.prubea.entity.TipoPregunta;
import com.bancolombia.prubea.repository.EncuestaRepository;
import com.bancolombia.prubea.repository.PreguntaERepository;
import com.bancolombia.prubea.repository.TipoEncuestaRepository;
import com.bancolombia.prubea.repository.TipoPreguntaRepository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EncuestaServiceImpl implements IEncuestaService {

    private final EncuestaRepository surveyRepository;
    private final PreguntaERepository questionRepository;
    private final TipoEncuestaRepository surveyTypeRepository;
    private final TipoPreguntaRepository questionTypeRepository;

    public EncuestaServiceImpl(EncuestaRepository surveyRepository, PreguntaERepository questionRepository,
                               TipoEncuestaRepository surveyTypeRepository, TipoPreguntaRepository questionTypeRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.surveyTypeRepository = surveyTypeRepository;
        this.questionTypeRepository = questionTypeRepository;
    }

    public ServiceResponseDto createSurvey(EncuestaDto surveyDto){
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        try{
            // buscar opcion TipoEncuesta en la DB por descripcion
           // TipoEncuesta surveyTypeFindInDbByDescription = surveyTypeRepository
             //       .findByDsTipoEncuestaIgnoreCase(surveyDto.getSurveyTypeDto().getDsSurveyType());
            // si existe, se envia TipoEncuestaDto y TipoEncuesta encontrado a TipoEncuestaDto
            TipoEncuesta surveyType = TipoEncuestaDto
                    .convertSurveyTypeDtoToSurveyType(surveyDto.getSurveyTypeDto());
            // Se envia EncuestaDto y TipoEncuesta al EncuestaDto
            Encuesta survey = surveyDto.convertSurveyDtoToSurvey(surveyDto, surveyType);
            // Se guarda entidad Encuesta que recibe fk TipoEncuesta y envia Fk a Pregunta
            surveyRepository.save(survey);
            // Se crean preguntas se envia encuesta del paso anterior y se asigna TipoPregunta
            Set<PreguntaE> questions = surveyDto.getQuestionDto().stream().map(questionEDto -> {
                // buscar opcion TipoPregunta en la DB por descripcion
//                TipoPregunta questionTypeFindIndDbByDescription = questionTypeRepository
//                        .findByDsTipoPregunta(questionEDto.getQuestionTypeDto().getDsQuestionType());
                // si existe, se envia el TipoPreguntaDto y TipoPregunta encontrado a TipoPreguntaDto
//                log.info("Tipo Pregunta: " + questionEDto.getQuestionTypeDto());
                TipoPregunta questionType = TipoPreguntaDto.convertQuestionTypeDtoToQuestionType
                        (questionEDto.getQuestionTypeDto());
                // se envian los datos necesarios para crear la pregunta y se retorna
                return questionEDto.convertQuestionSDtoToQuestionS(questionEDto, survey, questionType);
            }).collect(Collectors.toSet());
            // se guardan las preguntas con la relacion Encuesta/Pregunta
            questionRepository.saveAll(questions);
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
                String q = String.valueOf(surveyRepository.count());
                log.info(q);
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
        String id = idSurvey;
        try {
            if(surveyRepository.count() != 0 && surveyRepository.findById(id) != null){
                Encuesta survey = surveyRepository.findById(id).orElse(null);
//                Encuesta survey = surveyRepository.getEncuestaForId(id);
//                Encuesta survey = surveyRepository.findById(idSurvey).orElseThrow(() -> ExceptionPersonalizada);
                EncuestaDto surveyDto = EncuestaDto.convertSurveyToSurveyDto(survey);
                surveyDto.setSurveyTypeDto(TipoEncuestaDto.convertSurveyTypeToSurveyTypeDto(survey.getTipoEncuesta()));
                surveyDto.setQuestionDto(survey.getPreguntas().stream()
                        .map(PreguntaEDto::convertQuestionSToQuestionSDto).collect(Collectors.toSet()));
                surveyDto.setQuestionDto(surveyDto.getQuestionDto());
                data.put("objects", surveyDto);
                data.put("total", 1);
            }else{
                serviceResponseDto.setInfo("No se encuentran encuestas a listar!",
                        HttpStatus.NOT_FOUND.value(),Constants.MESSAGE);
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
                data.put("EncuestaDto", EncuestaDto.convertSurveyToSurveyDto(surveyRepository.getEncuestaForId(idEncuesta)));
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
