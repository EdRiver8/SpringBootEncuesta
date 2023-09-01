package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.ServiceResponseDto;

public interface IRespuestaService {
    ServiceResponseDto getAnswer();
    ServiceResponseDto getAllAnswers();
}
