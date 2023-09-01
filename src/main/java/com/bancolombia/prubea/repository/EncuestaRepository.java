package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.TipoEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EncuestaRepository extends JpaRepository<Encuesta, String> {

//    List<Encuesta> findByEsActiva(Boolean activa);

    @Query(value = "SELECT e FROM Encuesta e WHERE e.idEncuesta = :idEncuesta")
    Encuesta getEncuestaForId(@Param("idEncuesta") String idEncuesta);

}
