package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.Encuesta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(Mockito.class)
class EncuestaRepositoryTest {


//    private EncuestaRepository encuestaRepository;
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
}