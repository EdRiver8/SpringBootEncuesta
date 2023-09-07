package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.PreguntaEDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.repository.PreguntaERepository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PreguntaEServiceImpl implements IPreguntaEService {

    private final PreguntaERepository preguntaERepository;

    public PreguntaEServiceImpl(PreguntaERepository preguntaERepository){
        this.preguntaERepository = preguntaERepository;
    }

    /**
     * Method to list QuestionsE
     * @return ServiceResponseDto
     */
    @Override
    public ServiceResponseDto listQuestionS() {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        try {
            if(preguntaERepository.count() != 0){
                List<PreguntaE> preguntasE = preguntaERepository.findAll();
                List<PreguntaEDto> preguntasEDto = preguntasE.stream()
                        .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithoutAnswers)
                        .collect(Collectors.toList());
                data.put("objects", preguntasEDto);
                data.put("total", preguntaERepository.count());
            }else{
                serviceResponseDto.setInfo("No se encuentran preguntas a listar!",
                        HttpStatus.NOT_FOUND.value(),Constants.MESSAGE);
            }
        }catch (Exception e){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            data.put("message", "Error al listar las preguntas");
            log.error("listQuestionsS failed", e);
        }
        serviceResponseDto.setStatusCode(statusCode);
        serviceResponseDto.setData(data);
        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto findQuestionSById(String id) {
        return null;
    }

    @Override
    public ServiceResponseDto createQuestionS(PreguntaEDto preguntaEDto) {
        return null;
    }
}
