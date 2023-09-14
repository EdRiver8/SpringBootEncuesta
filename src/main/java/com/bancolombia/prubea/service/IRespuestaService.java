package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;

public interface IRespuestaService {
    ServiceResponseDto getAnswer();
    ServiceResponseDto getAllAnswers();
    ServiceResponseDto replySurvey(EncuestaDto surveyDto);
}
