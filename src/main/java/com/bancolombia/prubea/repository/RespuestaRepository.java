package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, String> {
//    @Query(value = "SELECT  c.nombre, o.fechaInicio, o.fechaFin, o.fuenteConocimiento.dsFuenteConocimiento, " +
//            "pre.dsPregunta, r.respuesta, pe.persona.nombre, pe.persona.apellido1 " +
//            "FROM Respuesta r " +
//            "INNER JOIN PreguntaE pre ON pre.idPregunta = r.pregunta.idPregunta " +
//            "INNER JOIN Encuesta e ON e.idEncuesta = pre.encuesta.idEncuesta " +
//            "INNER JOIN PersonaEncuesta pe ON pe.idPersonaEncuesta = r.personaEncuesta.idPersonaEncuesta " +
//            "INNER JOIN Oferta o ON o.idOferta = pe.referencia " +
//            "INNER JOIN Curso c ON c.idCurso = o.curso.idCurso " +
//            "WHERE r.idRespuesta = :idRespuesta ")
//    List<Object[]> reportDataSatisfation(@Param("idRespuesta") String idRespuesta);
//
//    @Query(value = "SELECT pre.dsPregunta, r.respuesta, pe.persona.nombre, pe.persona.apellido1 " +
//            "FROM Respuesta r " +
//            "INNER JOIN PreguntaE pre ON pre.idPregunta = r.pregunta.idPregunta " +
//            "INNER JOIN Encuesta e ON e.idEncuesta = pre.encuesta.idEncuesta " +
//            "INNER JOIN PersonaEncuesta pe ON pe.idPersonaEncuesta = r.personaEncuesta.idPersonaEncuesta " +
//            "WHERE r.idRespuesta = :idRespuesta ")
//    List<Object[]> reportDataPersonalized(@Param("idRespuesta") String idRespuesta);
}
