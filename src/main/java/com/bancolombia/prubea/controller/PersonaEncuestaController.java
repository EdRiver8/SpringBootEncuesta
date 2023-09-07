package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IPersonaEncuestaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Persona-Encuesta: Rutas Disponibles")
@RestController
@RequestMapping("/personaencuesta")
public class PersonaEncuestaController {

    private final IPersonaEncuestaService personaEncuestaService;

    public PersonaEncuestaController(IPersonaEncuestaService personaEncuestaService) {
        this.personaEncuestaService = personaEncuestaService;
    }

    @ApiOperation(value = "Lista todas las encuestas asignadas a una persona con un estado falso")
    @GetMapping("/listSurveyFalse/{idPerson}")
    public ResponseEntity<ControllerDto> listSurveyPersonWithStatusFalse(
            @ApiParam(value = "Id de la persona", example = "P3556725",
            required = true)@PathVariable String idPerson){
        ServiceResponseDto serviceResponseDto = personaEncuestaService.listSurveyPersonByIdPersonWhenStatusFalse(idPerson);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;

    }

    @ApiOperation(value = "Lista todas las encuestas asignadas a una persona con un estado true")
    @GetMapping("/listSurveyTrue/{idPerson}")
    public ResponseEntity<ControllerDto> listSurveyPersonWithStatusTrue(
            @ApiParam(value = "Id de la persona", example = "P3556725",
            required = true)@PathVariable String idPerson){
        ServiceResponseDto serviceResponseDto = personaEncuestaService.listSurveyPersonByIdPersonWhenStatusTrue(idPerson);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }


}
