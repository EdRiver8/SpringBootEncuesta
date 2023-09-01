package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.ITipoPreguntaService;
import com.bancolombia.prubea.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TipoPregunta")
public class TipoPreguntaController {

    private final ITipoPreguntaService tipoPreguntaService;

    public TipoPreguntaController(ITipoPreguntaService tipoPreguntaService) {
        this.tipoPreguntaService = tipoPreguntaService;
    }


    @GetMapping("/getTipoPregunta")
    public  ResponseEntity<ControllerDto> getlistTypeQuestions(){
        ServiceResponseDto serviceResponseDto = tipoPreguntaService.getlistTypeQuestions();
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(serviceResponseDto.getStatusCode()== Constants.INTERNAL_SERVER_ERROR_STATUS_CODE? HttpStatus.INTERNAL_SERVER_ERROR: HttpStatus.OK).body(controllerDto);
        return (response);
    }
}
