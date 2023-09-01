package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IRespuestaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Respuestas: Rutas disponibles")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/respuestas")
public class RespuestaController {

    private final IRespuestaService answerService;

    public RespuestaController(IRespuestaService answerService) {
        this.answerService = answerService;
    }

    @ApiOperation(value = "Lista la respuesta")
    @GetMapping("/get-answer")
    public ResponseEntity<ControllerDto> getAnswers(){
        ServiceResponseDto serviceResponseDto = answerService.getAnswer();
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Lista todas las respuestas")
    @GetMapping("/get-all-answers")
    public ResponseEntity<ControllerDto> listAnswers(){
        ServiceResponseDto serviceResponseDto = answerService.getAllAnswers();
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

}
