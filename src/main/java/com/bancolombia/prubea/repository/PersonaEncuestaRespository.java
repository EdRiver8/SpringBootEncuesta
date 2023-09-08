package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.PersonaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaEncuestaRespository extends JpaRepository<PersonaEncuesta, String> {

//    @Query (value = "SELECT pe FROM PersonaEncuesta pe WHERE pe.persona.idPersona LIKE :persona AND pe.esRespuesta = false")
//    List<PersonaEncuesta> listSurveyPersonByIdPersonWhenStatusFalse(@Param("persona") String persona);
//
//    @Query (value = "SELECT pe FROM PersonaEncuesta pe WHERE pe.persona.idPersona LIKE :persona AND pe.esRespuesta = true")
//    List<PersonaEncuesta> listSurveyPersonByIdPersonWhenStatusTrue(@Param("persona") String persona);

    @Query (value = "SELECT pe FROM PersonaEncuesta pe WHERE pe.encuesta.idEncuesta LIKE :idEncuesta")
    List<PersonaEncuesta> findSurveyPersonByIdSurvey(@Param("idEncuesta") String idEncuesta);
}
