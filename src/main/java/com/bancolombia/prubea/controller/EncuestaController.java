package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IEncuestaService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
            @ApiParam(example = "1",
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
            @ApiParam(example = "1",
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
            @ApiParam(example = "1",
                    required = true)@PathVariable(name = "idSurvey") String idSurvey){
        ServiceResponseDto serviceResponseDto = encuestaService.deleteSurvey(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }


    @GetMapping("/{encuestaId}/reporte-csv")
    public ResponseEntity<byte[]> generarInformeCsv(@PathVariable String encuestaId) throws IOException {
        byte[] informeCsv = encuestaService.generarReporteCsvEncuesta(encuestaId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "reporte_encuesta.csv");
        ResponseEntity response = new ResponseEntity<>(informeCsv, headers, HttpStatus.OK);
        return response;
//        return new ResponseEntity<>(informeCsv, headers, HttpStatus.OK);
    }

    @GetMapping("/reporte-csv/{encuestaId}")
    @ApiOperation(value = "Este metodo aun no funciona porque faltan las entidades y tablas con datos en el SQL para Curso y Oferta para finalizarlo")
    public ResponseEntity<byte[]> getCsvReport(@PathVariable String encuestaId) throws IOException {
        ServiceResponseDto serviceResponseDto = encuestaService.generarReporteCsvEncuesta2(encuestaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "reporte_"
                + serviceResponseDto.getData().get("title") + ".csv");
        Map<String, Object> data = (Map<String, Object>) serviceResponseDto.getData();
//        Map<String, Object> data = (Map<String, Object>) controllerDto.getBody().get("data");
        ResponseEntity response = new ResponseEntity<>(data.get("objects"), headers, HttpStatus.OK);
        return response;
    }

    @ApiOperation(value = "Activacion de una encuesta por su ID")
    @PutMapping("/activate-survey/{idSurvey}")
    public ResponseEntity<ControllerDto> activateSurvey(@PathVariable(name = "idSurvey") String idSurvey){
        ServiceResponseDto serviceResponseDto = encuestaService.activateSurvey(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Al actualizar una encuesta valida que no halla una persona asignada a ella")
    @GetMapping("/validateAssign/{idSurvey}")
    public ResponseEntity<ControllerDto> validateAssignSurveyToPerson(
            @PathVariable(name = "idSurvey") String idSurvey) {
        ServiceResponseDto serviceResponseDto = encuestaService.validateAssignSurveyToPerson(idSurvey);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

    @ApiOperation(value = "Actualización de una encuesta")
    @PostMapping("/update-survey")
    public ResponseEntity<ControllerDto> updateSurvey(@RequestBody EncuestaDto surveyDto) {
        ServiceResponseDto serviceResponseDto = encuestaService.updateSurvey(surveyDto);
        ControllerDto controllerDto = new ControllerDto();
        controllerDto.setStatusCode(serviceResponseDto.getStatusCode());
        controllerDto.setBody(serviceResponseDto.getStatusCode(), serviceResponseDto.getData(), true);
        ResponseEntity<ControllerDto> response = ResponseEntity.status(HttpStatus.OK).body(controllerDto);
        return response;
    }

}
