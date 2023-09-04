package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.service.EncuestaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncuestaRepositoryTest {


    @Mock
    private EncuestaRepository encuestaRepository;

    @InjectMocks
    private EncuestaServiceImpl encuestaService;
//
//    public EncuestaRepositoryTest(EncuestaRepository encuestaRepository) {
//        this.encuestaRepository = encuestaRepository;
//    }
//
//    @Test
//    void itShouldCheckIfASurveyExistsById() {
//        // Given
//        Encuesta actual = Encuesta.builder()
//                .idEncuesta("abc123")
//                .nombreEncuesta("Encuesta")
//                .dsEncuesta("Otra encuesta mas")
//                .cantidadPreguntas(3)
//                .estado("Activa")
//                .build();
//        // When
////        Mockito.when()
//        encuestaRepository.save(actual);
//        Encuesta esperado = encuestaRepository.getEncuestaForId(actual.getIdEncuesta());
//        // Then
//        assertEquals(esperado, actual, "Las encuestas no son iguales");
//    }
    @Test
    void testDummyForMommy(){
        int a = 2;
        int b = 3;
        assertEquals(a, b,"son diferentes");
    }

    @Test
    void getSurveyByIdTest(){
        final String surveyId = UUID.randomUUID().toString();
        final Encuesta survey = new Encuesta();
        Mockito.when(encuestaRepository.getEncuestaForId(surveyId)).thenReturn(survey);


    }
}