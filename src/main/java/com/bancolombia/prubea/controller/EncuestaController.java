package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IEncuestaService;
import com.bancolombia.prubea.util.Constants;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Encuestas: Rutas disponibles")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/encuestas")
public class EncuestaController {

    private final IEncuestaService encuestaService;

    public EncuestaController(IEncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }

    @ApiOperation(value = "Guardar encuesta", notes = "Se envia el cuerpo de la encuesta para ser almacenado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Se guardo la encuesta en la DB"),
            @ApiResponse(code = 400, message = "Verifica los datos, no se guardo la encuesta"),
            @ApiResponse(code = 404, message = "No se encontró el elemento")
    })
    @PostMapping("/add")
    public ResponseEntity<ControllerDto> create(@RequestBody EncuestaDto surveyDto){
        ServiceResponseDto serviceResponseDto = encuestaService.createSurvey(surveyDto);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Lista todas las encuestas")
    @GetMapping("/get-all")
    public ResponseEntity<ControllerDto> listSurvey(){
        ServiceResponseDto serviceResponseDto = encuestaService.listSurvey();
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);

        return response;
    }

    @ApiOperation(value = "Busca una encuesta por su id y la trae con sus preguntas")
    @GetMapping("/get-all-with-questions/{idSurvey}")
    public ResponseEntity<ControllerDto> listSurveyWithQuestions(
            @ApiParam(example = "8a466d33-33cb-48e7-b0f0-8b425deda0b4",
                    required = true)@PathVariable(name = "idSurvey") String idSurvey){
        ServiceResponseDto serviceResponseDto = encuestaService.listFullSurvey(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);

        return response;
    }

//    @ApiOperation(value = "Busca una encuesta por su id")
//    @GetMapping("{id}")
//    public ResponseEntity<ControllerDto> getSurveyById(
//        @ApiParam(value = "Id de la encuesta", example = "703e98ad-6c87-44c7-b92a-49c904970e16", required = true) @PathVariable String idSurvey){
//        ServiceResponseDto serviceResponseDto = encuestaService.findSurveyById(idSurvey);
//        ControllerDto controllerDto = new ControllerDto();
//        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
//        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
//        ResponseEntity<ControllerDto> response = ResponseEntity.status(Constants.SUCCESS_STATUS_CODE).body(controllerDto);
//        return response;
//    }

}
