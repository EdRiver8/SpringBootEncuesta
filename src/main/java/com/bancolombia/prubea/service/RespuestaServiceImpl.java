package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.RespuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.dto.TipoEncuestaDto;
import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.entity.Respuesta;
import com.bancolombia.prubea.entity.TipoEncuesta;
import com.bancolombia.prubea.repository.RespuestaRepository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RespuestaServiceImpl implements IRespuestaService{

    private final RespuestaRepository answerRepository;

    public RespuestaServiceImpl(RespuestaRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

//    @Override
//    public ServiceResponseDto getAnswer() {
//        int statusCode = Constants.SUCCESS_STATUS_CODE;
//        Map<String,Object> data = new LinkedHashMap<>();
//        try{
//            if(!answerRepository.findAll().isEmpty()) {
//                Respuesta answers = answerRepository.findAll();
//                RespuestaDto answersDto = RespuestaDto.convertAnswerToAnswerDto()
//                data.put("Total ", answerRepository.count());
//                data.put("Respuestas ", answersDto);
//            }else {
//                data.put("message", "No hay respuestas para mostrar");
//            }
//        }catch(Exception error){
//            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
//            log.error("getAnswers failed", error);
//        }
//        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
//        serviceResponseDto.setStatusCode((int) statusCode);
//        serviceResponseDto.setData((Map<String, Object>) data);
//        return serviceResponseDto;
//    }

    @Override
    public ServiceResponseDto getAnswer() {
        return null;
    }

    @Override
    public ServiceResponseDto getAllAnswers() {
        return null;
    }

    @Override
    public ServiceResponseDto replySurvey(EncuestaDto surveyDto) {
        return null;
    }

//    @Override
//    public ServiceResponseDto replySurvey(EncuestaDto surveyDto) {
//        int statusCode = Constants.SUCCESS_STATUS_CODE;
//        Map<String, Object> data = new LinkedHashMap<String, Object>();
//        try{
//            RespuestaDto respuestaDto = new RespuestaDto();
//            //Recibe el idPersonaEncuesta
//            String idSurveyPerson = surveyDto.getIdSurveyPerson();
//            surveyDto.getQuestionDto().stream().forEach(questionEDto -> {
//                // Se obtiene cada pregunta
//                PreguntaE question = questionEDto.convertQuestionDtoToQuestionForReply(questionEDto);
//                // Se crea una respuesta
//                Respuesta answers = respuestaDto.convertAnswerDtoToAnswer(respuestaDto, question, idSurveyPerson);
//                answerRepository.save(answers);
//            });
//        }
//        catch(Exception e) {
//            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
//            data.put("message", "Error al guardar las respuestas");
//            log.error("replySurvey", e);
//        }
//        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
//        serviceResponseDto.setStatusCode(statusCode);
//        serviceResponseDto.setData(data);
//        return serviceResponseDto;
//    }

//    @Override
//    public ServiceResponseDto getAllAnswers() {
//        int statusCode = Constants.SUCCESS_STATUS_CODE;
//        Map<String,Object> data = new LinkedHashMap<>();
//        try{
//            if(!answerRepository.findAll().isEmpty()) {
//                List<Respuesta> answers = answerRepository.findAll();
//                Set<RespuestaDto> answersDto = answers.stream()
//                        .map(RespuestaDto::convertAnswerToAnswerDto).collect(Collectors.toSet());
//                data.put("Total ", answerRepository.count());
//                data.put("Respuestas ", answersDto);
//            }else {
//                data.put("message", "No hay respuestas para mostrar");
//            }
//        }catch(Exception error){
//            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
//            log.error("getAnswers failed", error);
//        }
//        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
//        serviceResponseDto.setStatusCode((int) statusCode);
//        serviceResponseDto.setData((Map<String, Object>) data);
//        return serviceResponseDto;
//    }
}
