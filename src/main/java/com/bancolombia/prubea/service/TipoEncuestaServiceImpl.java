package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.dto.TipoEncuestaDto;
import com.bancolombia.prubea.entity.TipoEncuesta;
import com.bancolombia.prubea.repository.TipoEncuestaRepository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
public class TipoEncuestaServiceImpl implements ITipoEncuestaService {

    private final TipoEncuestaRepository tipoEncuestaRepository;

    @Autowired
    public TipoEncuestaServiceImpl(TipoEncuestaRepository tipoEncuestaRepository) {this.tipoEncuestaRepository = tipoEncuestaRepository;}

    @Override
    public ServiceResponseDto getlistTypeSurvey() {
        int statusCode = Constants.SUCCESS_STATUS_CODE;
        Map<String,Object> data=new LinkedHashMap<>();
        try{
            List<TipoEncuesta> tipoEncuestaList = tipoEncuestaRepository.findAll();
            List<TipoEncuestaDto> tipoEncuestaDTOList = new ArrayList<>();
            if(!tipoEncuestaList.isEmpty()) {
                tipoEncuestaList.forEach(tipoEncuesta->{
                    TipoEncuestaDto tipoEncuestaDTO = new TipoEncuestaDto();
                    tipoEncuestaDTO.setDsSurveyType(tipoEncuesta.getDsTipoEncuesta());
                    tipoEncuestaDTOList.add(tipoEncuestaDTO);
                });
                data.put("Descripcion Tipo Encuesta",tipoEncuestaDTOList);
            }else{
                data.put("message", "No hay tipos de Encuestas disponibles");
            }

        }catch(Exception error){
            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
            log.error("getlistTypeSurvey failed", error);
        }
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setStatusCode((int) statusCode);
        serviceResponseDto.setData((Map<String, Object>) data);
        return serviceResponseDto;
    }

}
