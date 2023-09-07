package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.TipoEncuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface EncuestaRepository extends JpaRepository<Encuesta, String> {

//    List<Encuesta> findByEsActiva(Boolean activa);

    @Query(value = "SELECT e FROM Encuesta e WHERE e.idEncuesta = :idEncuesta")
    Encuesta getEncuestaForId(@Param("idEncuesta") String idEncuesta);

    Boolean findByTipoEncuestaAndEsEncuesta(TipoEncuesta surveyType, String state);

    @Query(value= "SELECT e FROM Encuesta e WHERE e.esEncuesta = :state AND e.tipoEncuesta.idTipoEncuesta = :idSurveyType")
    Optional<Encuesta> findByTipoEncuestaEsEncuesta(@Param("idSurveyType")String idSurveyType, @Param("state")String state);

    Page<Encuesta> findAllByEsEncuesta(Pageable pageable, String state);

}
