package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.repository.EncuestaRepository;
import com.bancolombia.prubea.util.Constants;
import cucumber.api.java.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

//class EncuestaServiceImplTest {
//
//
//    // Inicializamos la instancia del servicio que queremos probar
//    @InjectMocks
//    private EncuestaServiceImpl encuestaService;
//
//    // Creamos un objeto mock del repositorio que el servicio utiliza
//    @Mock
//    private EncuestaRepository surveyRepository;
//
//    // Método que se ejecuta antes de cada prueba para inicializar los objetos mock
//    @Before
//    public void setUp() {
//
//    }
//
//    // Prueba para verificar el comportamiento cuando hay una encuesta activa
//    @Test
//    public void testValidateActiveSatisfactionSurveyWithActiveSurvey() {
//        // Simulamos una encuesta activa en el repositorio
//        Encuesta activeSurvey = new Encuesta();
//        activeSurvey.setIdEncuesta(1L);
//        when(surveyRepository.count()).thenReturn(1L);
//        when(surveyRepository.findByTipoEncuestaEsEncuesta("2", "Activada")).thenReturn(Optional.of(activeSurvey));
//
//        // Ejecutamos el servicio
//        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();
//
//        // Verificamos los resultados esperados
//        assertEquals(Constants.CONFLICT_WITH_CURRENT_STATE, response.getStatusCode());
//        assertNotNull(response.getData().get("objects"));
//        assertEquals(1, (int) response.getData().get("total"));
//        assertEquals("Ya hay una encuesta Activa de satisfaccion", response.getData().get("message"));
//    }
//
//    // Prueba para verificar el comportamiento cuando no hay una encuesta activa
//    @Test
//    public void testValidateActiveSatisfactionSurveyWithNoActiveSurvey() {
//        // Simulamos que no hay una encuesta activa en el repositorio
//        when(surveyRepository.count()).thenReturn(0L);
//
//        // Ejecutamos el servicio
//        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();
//
//        // Verificamos los resultados esperados
//        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
//        assertEquals("No existe encuesta de satisfaccion activa, puedes crear la encuesta!", response.getData().get("message"));
//    }
//
//    // Prueba para verificar el comportamiento cuando se produce un error en el repositorio
//    @Test
//    public void testValidateActiveSatisfactionSurveyWithError() {
//        // Simulamos un error al acceder al repositorio
//        when(surveyRepository.count()).thenThrow(new RuntimeException("Simulated error"));
//
//        // Ejecutamos el servicio
//        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();
//
//        // Verificamos los resultados esperados
//        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, response.getStatusCode());
//        assertEquals("Error al buscar una encuesta de satisfaccion activa!", response.getData().get("message"));
//
//        // Verificamos que el error se haya registrado correctamente
//        verify(encuestaService, times(1)).log.error("validateActiveSatisfactionSurvey failed", any(Exception.class));
//    }
//
//}