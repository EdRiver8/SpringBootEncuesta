package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.PreguntaEDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;

public interface IPreguntaEService {
    ServiceResponseDto findQuestionSById(String id);
    ServiceResponseDto createQuestionS(PreguntaEDto preguntaEDto);
    ServiceResponseDto listQuestionS();
}
