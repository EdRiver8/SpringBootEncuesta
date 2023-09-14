package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IEncuestaService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Encuestas: Rutas Disponibles")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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
    @PostMapping("/create")
    public ResponseEntity<ControllerDto> create(@RequestBody EncuestaDto surveyDto){
        ServiceResponseDto serviceResponseDto = encuestaService.createSurvey(surveyDto);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Al intentar crear una encuesta de satisfaccion, valida que no haya una de este tipo activa")
    @GetMapping("/validate-active-survey")
    public ResponseEntity<ControllerDto> getValidateActiveSurvey(@RequestParam(required = true) String idSurveyType){
        ServiceResponseDto serviceResponseDto = encuestaService.validateActiveSatisfactionSurvey(idSurveyType);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    // necesita habilitar el campo de 'answerDto' en RespuestaDto
    @ApiOperation(value = "Guardar encuesta con sus respectivas preguntas",
            notes = "Se envia el cuerpo de la encuesta para ser almacenado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Se guardo la encuesta en la DB"),
            @ApiResponse(code = 400, message = "Verifica los datos, no se guardo la encuesta"),
            @ApiResponse(code = 404, message = "No se encontró el elemento")
    })
    @PostMapping("/creat-with-answers")
    public ResponseEntity<ControllerDto> createWithAnswers(@RequestBody EncuestaDto surveyDto){
        ServiceResponseDto serviceResponseDto = encuestaService.createSurveyWithAnswers(surveyDto);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Lista todas las encuestas sin preguntas, Param Optional: Page, Size, Sort")
    @GetMapping("/list-all-survey")
    public ResponseEntity<ControllerDto> listSurvey(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "5") int size,
                                                    @RequestParam(defaultValue = "esEncuesta") String sort){
        ServiceResponseDto serviceResponseDto = encuestaService.listSurveyWithoutQuestions(page, size, sort);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Busca una encuesta por su id y la trae con sus preguntas")
    @GetMapping("/get-questions/{idSurvey}")
    public ResponseEntity<ControllerDto> listSurveyWithQuestions(
            @ApiParam(example = "11bb12af-b8b4-4b2f-893e-3e41921c0d8e",
                    required = true)@PathVariable(name = "idSurvey") String idSurvey){
        ServiceResponseDto serviceResponseDto = encuestaService.getSurveyWithQuestions(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Busca una encuesta por su id y la trae con preguntas y respectivas respuestas")
    @GetMapping("/get-questions-answers/{idSurvey}")
    public ResponseEntity<ControllerDto> listSurveyWithQuestionsAndAnswers(
            @ApiParam(example = "11bb12af-b8b4-4b2f-893e-3e41921c0d8e",
                    required = true)@PathVariable(name = "idSurvey") String idSurvey){
        ServiceResponseDto serviceResponseDto = encuestaService.getSurveyWithQuestionsAndAnswers(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Eliminar una encuesta por el id sino cuenta ya con respuestas")
    @DeleteMapping("/delete/{idSurvey}")
    public ResponseEntity<ControllerDto> deleteSurvey(
            @ApiParam(example = "11bb12af-b8b4-4b2f-893e-3e41921c0d8e",
                    required = true)@PathVariable(name = "idSurvey") String idSurvey){
        ServiceResponseDto serviceResponseDto = encuestaService.deleteSurvey(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

}
