package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.ITipoEncuestaService;
import com.bancolombia.prubea.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Tipo Encuesta: Rutas Disponibles")
@RestController
@RequestMapping("/tipoEncuesta")
public class TipoEncuestaController {

    @Autowired
    private final ITipoEncuestaService tipoEncuestaService;

    public TipoEncuestaController(ITipoEncuestaService tipoEncuestaService) {
        this.tipoEncuestaService = tipoEncuestaService;
    }
    /**
     * Method to get all survey
     * @return ResponseEntity<ControllerDto>
     */
    @GetMapping("/listTypeSurvey")
    public ResponseEntity<ControllerDto> getlistTypeSurvey(){
        ServiceResponseDto serviceResponseDto = tipoEncuestaService.getlistTypeSurvey();
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(serviceResponseDto.getStatusCode()== Constants.INTERNAL_SERVER_ERROR_STATUS_CODE? HttpStatus.INTERNAL_SERVER_ERROR: HttpStatus.OK).body(controllerDto);
        return (response);

    }
}
