package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.TipoEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEncuestaRepository extends JpaRepository<TipoEncuesta,String> {

    TipoEncuesta findByDsTipoEncuestaIgnoreCase(String dsSurveyType);

//    @Query("SELECT te FROM TipoEncuesta te WHERE te.dsTipoEncuesta = :dsSurveyType")
//    TipoEncuesta findByDescripcion(@Param("dsSurveyType") String dsSurveyType);

}
