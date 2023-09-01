package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.dto.TipoPreguntaDto;
import com.bancolombia.prubea.entity.TipoPregunta;
import com.bancolombia.prubea.repository.TipoPreguntaRepository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class TipoPreguntaServiceImpl implements ITipoPreguntaService {

    private final TipoPreguntaRepository tipoPreguntaRepository;

    @Autowired
    public TipoPreguntaServiceImpl(TipoPreguntaRepository tipoPreguntaRepository) {this.tipoPreguntaRepository = tipoPreguntaRepository;}

    @Override
    public ServiceResponseDto getlistTypeQuestions() {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String,Object> data=new LinkedHashMap<>();
        try{
            List<TipoPregunta> tipoPreguntaList= tipoPreguntaRepository.findAll();
            List<TipoPreguntaDto> tipoPreguntaDTOList = new ArrayList<>();
            if(!tipoPreguntaList.isEmpty()) {
                tipoPreguntaList.forEach(tipoPregunta->{
                    TipoPreguntaDto tipoPreguntaDTO = new TipoPreguntaDto();
                    tipoPreguntaDTO.setDsQuestionType(tipoPregunta.getDsTipoPregunta());
                    tipoPreguntaDTOList.add(tipoPreguntaDTO);
                });
                data.put("Descripcion Tipo Pregunta",tipoPreguntaDTOList);
            }else{
                data.put("message", "No hay tipos de preguntas disponibles");
            }

        }catch(Exception error){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            log.error("getAllMcqDiagnosticType failed", error);
        }
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setStatusCode((int) statusCode);
        serviceResponseDto.setData((Map<String, Object>) data);
        return serviceResponseDto;
    }



}

