package com.bancolombia.prubea.controller;

import com.bancolombia.prubea.dto.ControllerDto;
import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.service.IEncuestaService;
import cucumber.api.java.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EncuestaControllerTest {

    @InjectMocks
    private EncuestaController encuestaController;
    @Mock
    private IEncuestaService encuestaService;

    // Creacion de variables y objetos en comun
    EncuestaDto activeSurveyDto;
    ServiceResponseDto serviceResponseDto;
    Map<String, Object> data;
    Map<String, Object> msgService;

    // Método que se ejecuta antes de cada prueba para inicializar los objetos mockeados
    @Before
    public void setUp() {
        activeSurveyDto = EncuestaDto.builder()
                .idSurvey("1")
                .surveyName("Prueba")
                .dsSurvey("Cualquier cosa")
                .state("Activada")
                .amountQuestions(1)
                .build();

        serviceResponseDto = new ServiceResponseDto();

        data = new LinkedHashMap<String, Object>();
        msgService = new LinkedHashMap<>();
    }

    @Test
    public void testCreateSurveySuccess() {
        serviceResponseDto.setStatusCode(200);
        data.put("message", "encuesta creada con exito!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.createSurvey(activeSurveyDto)).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.create(activeSurveyDto);
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
        assertEquals("encuesta creada con exito!", msgService.get("message"));

    }

    @Test
    public void testCreateSurveyWithBadRequest() {
        serviceResponseDto.setStatusCode(400);
        data.put("message", "La encuesta no cuenta con preguntas");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.createSurvey(activeSurveyDto)).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.create(activeSurveyDto);
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(400, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("BAD_REQUEST", controllerDto.getBody().getBody().get("message"));
        assertEquals("Some params are missing", controllerDto.getBody().getBody().get("description"));
        assertEquals("La encuesta no cuenta con preguntas", msgService.get("message"));
    }

    @Test
    public void testCreateSurveyWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);
        data.put("message", "Error al tratar de crear la encuesta!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
        assertEquals("Error al tratar de crear la encuesta!", msgService.get("message"));
    }

    @Test
    public void getValidateActiveSurveySuccess() {
        serviceResponseDto.setStatusCode(200);
        data.put("message", "No existe encuesta de satisfaccion activa, puedes crear la encuesta!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
        assertEquals("No existe encuesta de satisfaccion activa, puedes crear la encuesta!", msgService.get("message"));

    }


    @Test
    public void getValidateActiveSurveyWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);
        data.put("message", "Error al buscar una encuesta de satisfaccion activa!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
        assertEquals("Error al buscar una encuesta de satisfaccion activa!", msgService.get("message"));
    }

    @Test
    public void getValidateActiveSurveyWithConflictWithCurrentState() {
        serviceResponseDto.setStatusCode(409);
        data.put("message", "Ya hay una encuesta Activa de satisfaccion");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(409, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("CONFLICT", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource already exists", controllerDto.getBody().getBody().get("description"));
        assertEquals("Ya hay una encuesta Activa de satisfaccion", msgService.get("message"));
    }

    @Test
    public void listSurveySucess() {
        serviceResponseDto.setStatusCode(200);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.listSurveyWithoutQuestions(1, 5, "")).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurvey(1, 5, "");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithNotFound() {
        serviceResponseDto.setStatusCode(404);
        data.put("message", "No se encuentran encuestas para listar!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.listSurveyWithoutQuestions(1, 5, "")).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurvey(1, 5, "");
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");


        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
        assertEquals("No se encuentran encuestas para listar!", msgService.get("message"));
    }

    @Test
    public void listSurveyWithInternalError() {
        serviceResponseDto.setStatusCode(500);
        data.put("message", "Error al listar las encuestas");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.listSurveyWithoutQuestions(1, 5, "")).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurvey(1, 5, "");
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
        assertEquals("Error al listar las encuestas", msgService.get("message"));
    }

    @Test
    public void listSurveyWithQuestionsSucces() {
        serviceResponseDto.setStatusCode(200);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestions(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestions(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithQuestionsWithNotFound() {
        serviceResponseDto.setStatusCode(404);
        data.put("message", "No se encuentra la encuesta buscada!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestions(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestions(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
        assertEquals("No se encuentra la encuesta buscada!", msgService.get("message"));
    }

    @Test
    public void listSurveyWithQuestionsWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);
        data.put("message", "Error al buscar la encuesta");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestions(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestions(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
        assertEquals("Error al buscar la encuesta", msgService.get("message"));
    }



    @Test
    public void listSurveyWithQuestionsAndAnswersSuccess() {
        serviceResponseDto.setStatusCode(200);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithQuestionsAndAnswersWithNotContent() {
        serviceResponseDto.setStatusCode(204);
        data.put("message", "La encuesta existe, pero no tiene respuestas asociadas!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(204, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_CONTENT", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not exists", controllerDto.getBody().getBody().get("description"));
        assertEquals("La encuesta existe, pero no tiene respuestas asociadas!", msgService.get("message"));
    }

    @Test
    public void listSurveyWithQuestionsAndAnswersWithNotFound() {
        serviceResponseDto.setStatusCode(404);
        data.put("message", "No se encuestra la encuesta buscada!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
        assertEquals("No se encuestra la encuesta buscada!", msgService.get("message"));

    }

    @Test
    public void listSurveyWithQuestionsAndAnswersWithinternalServerError() {
        serviceResponseDto.setStatusCode(500);
        data.put("message", "Error al buscar la encuesta");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
        assertEquals("Error al buscar la encuesta", msgService.get("message"));
    }

    @Test
    public void deleteSurveySucces() {
        serviceResponseDto.setStatusCode(200);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.deleteSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.deleteSurvey(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void deleteSurveyWithNotFound() {
        serviceResponseDto.setStatusCode(404);
        data.put("message", "La encuesta buscada, no se encuentra en la db");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.deleteSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.deleteSurvey(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
        assertEquals("La encuesta buscada, no se encuentra en la db", msgService.get("message"));
    }

    @Test
    public void deleteSurveyWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);
        data.put("message", "Error al buscar la encuesta");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.deleteSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.deleteSurvey(anyString());
        msgService = (Map<String, Object>) controllerDto.getBody().getBody().get("data");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
        assertEquals("Error al buscar la encuesta", msgService.get("message"));
    }
}