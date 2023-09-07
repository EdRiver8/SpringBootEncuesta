package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.PersonaEncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;

public interface IPersonaEncuestaService {
    ServiceResponseDto updateSurveyStatus(PersonaEncuestaDto personaEncuestaDto);
    ServiceResponseDto listSurveyPersonByIdPersonWhenStatusFalse(String idSurveyPerson);
    ServiceResponseDto listSurveyPersonByIdPersonWhenStatusTrue(String idSurveyPerson);
}
