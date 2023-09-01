package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.PreguntaE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaERepository extends JpaRepository<PreguntaE, String>{

//    @Query(value = "SELECT p FROM PreguntaE p WHERE p.id_pregunta = :idPregunta")
//    PreguntaE getPreguntaForId(@Param("idPregunta") String idPregunta);


}
