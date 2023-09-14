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
    }

    @Test
    public void testCreateSurveySuccess() {
        serviceResponseDto.setStatusCode(200);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.createSurvey(activeSurveyDto)).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.create(activeSurveyDto);

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));

    }

    @Test
    public void testCreateSurveyWithBadRequest() {
        serviceResponseDto.setStatusCode(400);
        data.put("message", "encuesta creada con exito!");
        serviceResponseDto.setData(data);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.createSurvey(activeSurveyDto)).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.create(activeSurveyDto);

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(400, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("BAD_REQUEST", controllerDto.getBody().getBody().get("message"));
        assertEquals("Some params are missing", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void testCreateSurveyWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey()).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey();

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void getValidateActiveSurveySuccess() {
        serviceResponseDto.setStatusCode(200);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey()).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey();

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(200, controllerDto.getBody().getStatusCode());
        assertEquals("success", controllerDto.getBody().getBody().get("status"));
        assertEquals("SUCCESS", controllerDto.getBody().getBody().get("message"));
        assertEquals("Service executed successfully", controllerDto.getBody().getBody().get("description"));
    }


    @Test
    public void getValidateActiveSurveyWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey()).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey();

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void getValidateActiveSurveyWithConflictWithCurrentState() {
        serviceResponseDto.setStatusCode(409);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.validateActiveSatisfactionSurvey()).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.getValidateActiveSurvey();

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(409, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("CONFLICT", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource already exists", controllerDto.getBody().getBody().get("description"));
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

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.listSurveyWithoutQuestions(1, 5, "")).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurvey(1, 5, "");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithInternalError() {
        serviceResponseDto.setStatusCode(500);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.listSurveyWithoutQuestions(1, 5, "")).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurvey(1, 5, "");

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
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

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestions(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestions(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithQuestionsWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestions(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestions(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
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

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(204, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_CONTENT", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not exists", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithQuestionsAndAnswersWithNotFound() {
        serviceResponseDto.setStatusCode(404);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void listSurveyWithQuestionsAndAnswersWithinternalServerError() {
        serviceResponseDto.setStatusCode(500);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.getSurveyWithQuestionsAndAnswers(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.listSurveyWithQuestionsAndAnswers(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
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

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.deleteSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.deleteSurvey(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(404, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("NOT_FOUND", controllerDto.getBody().getBody().get("message"));
        assertEquals("Resource not found", controllerDto.getBody().getBody().get("description"));
    }

    @Test
    public void deleteSurveyWithInternalServerError() {
        serviceResponseDto.setStatusCode(500);

        // Configurar el comportamiento del servicio para simular la creación exitosa
        when(encuestaService.deleteSurvey(anyString())).thenReturn(serviceResponseDto);

        // Realizar la solicitud POST con el DTO válido
        ResponseEntity<ControllerDto> controllerDto = encuestaController.deleteSurvey(anyString());

        // Verificar que se haya devuelto el cuerpo configurado en el serviceResponseDto
        assertEquals("200 OK", controllerDto.getStatusCode().toString());
        assertEquals(500, controllerDto.getBody().getStatusCode());
        assertEquals("error", controllerDto.getBody().getBody().get("status"));
        assertEquals("INTERNAL_SERVER_ERROR", controllerDto.getBody().getBody().get("message"));
        assertEquals("Internal server error", controllerDto.getBody().getBody().get("description"));
    }
}