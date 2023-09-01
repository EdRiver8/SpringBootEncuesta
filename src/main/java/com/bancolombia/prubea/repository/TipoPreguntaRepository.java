package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.TipoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoPreguntaRepository extends JpaRepository<TipoPregunta,String> {
    TipoPregunta findByDsTipoPregunta(String dsQuestionType);
}
