package com.bancolombia.prubea.repository;

import com.bancolombia.prubea.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, String> {
}
