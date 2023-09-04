package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.TipoEncuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class EncuestaRepositoryTest2 {
    @Autowired
    private EncuestaRepository encuestaRepository;

    @BeforeEach
    void setup(){
        System.out.println("Hooooooooooooooooooooooooooooooooooooooola");
    }

    @DisplayName("Test que se encarga de guardar una encuesta")
    @Test
    void testGuardarEncuesta(){
        // Given: Dada una condicion previa o su configuracion
        TipoEncuesta tipoEncuesta = TipoEncuesta.builder()
                .idTipoEncuesta("abc123")
                .dsTipoEncuesta("Personalizada")
                .build();

        Encuesta encuesta = Encuesta.builder()
                .idEncuesta(UUID.randomUUID().toString())
                .nombreEncuesta("Encuesta 1")
                .dsEncuesta("Encuesta del curso...")
                .cantidadPreguntas(3)
                .estado("Activa")
                .tipoEncuesta(tipoEncuesta)
                .build();

        // When: La accion/proceso/metodo/comportamiento a probar
        Encuesta encuestaGuardada = encuestaRepository.save(encuesta);

        // Then: Lo que se desea verificar, la salida esperada
        assertNotNull(encuestaGuardada);
    }
}