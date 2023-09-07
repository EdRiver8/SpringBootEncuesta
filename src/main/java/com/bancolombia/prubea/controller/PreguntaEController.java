package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IPreguntaEService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Pregunta: Rutas Disponibles")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/preguntas")
public class PreguntaEController {
    private final IPreguntaEService preguntaEService;

    public PreguntaEController(IPreguntaEService preguntaEService) { this.preguntaEService = preguntaEService; }

    @GetMapping("/list")
    public ResponseEntity<ControllerDto> listQuestionsS(){
        ServiceResponseDto serviceResponseDto = preguntaEService.listQuestionS();
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);

        return response;
    }
}
