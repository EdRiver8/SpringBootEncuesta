package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;

import java.io.IOException;

public interface IEncuestaService {
    ServiceResponseDto createSurvey(EncuestaDto encuestaDto);
    ServiceResponseDto createSurveyWithAnswers(EncuestaDto encuestaDto);
    ServiceResponseDto listSurveyWithoutQuestions(int page, int size, String sortBy);
    ServiceResponseDto findSurveyById(String idEncuesta);
    ServiceResponseDto getSurveyWithQuestions(String idSurvey);
    ServiceResponseDto getSurveyWithQuestionsAndAnswers(String idSurvey);
    ServiceResponseDto updateSurvey(EncuestaDto encuestaDto);
    ServiceResponseDto deleteSurvey(String idEncuesta);
    ServiceResponseDto activateSurvey(String idSurvey);
    ServiceResponseDto deactivateSurvey(String idEncuesta);
    ServiceResponseDto validateActiveSatisfactionSurvey(String idSurveyType);
    public ServiceResponseDto validateAssignSurveyToPerson(String idSurvey);
    byte[] generarReporteCsvEncuesta(String surveyId) throws IOException;
    ServiceResponseDto generarReporteCsvEncuesta2(String surveyId) throws IOException;
}
