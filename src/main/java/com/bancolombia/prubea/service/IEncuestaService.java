package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;

public interface IEncuestaService {
    ServiceResponseDto createSurvey(EncuestaDto encuestaDto);
    ServiceResponseDto listSurvey();
    ServiceResponseDto findSurveyById(String idEncuesta);
    ServiceResponseDto listFullSurvey(String idSurvey);
    ServiceResponseDto updateSurvey(EncuestaDto encuestaDto);
    ServiceResponseDto deleteSurvey(String idEncuesta);
    ServiceResponseDto activateSurvey(String idEncuesta);
    ServiceResponseDto deactivateSurvey(String idEncuesta);
}
