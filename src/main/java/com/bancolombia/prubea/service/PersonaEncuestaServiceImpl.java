package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.PersonaEncuestaDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.entity.PersonaEncuesta;
import com.bancolombia.prubea.repository.PersonaEncuestaRespository;
import com.bancolombia.prubea.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonaEncuestaServiceImpl implements IPersonaEncuestaService {

    private final PersonaEncuestaRespository personaEncuestaRespository;

    public PersonaEncuestaServiceImpl(PersonaEncuestaRespository personaEncuestaRespository)
    {
        this.personaEncuestaRespository = personaEncuestaRespository;
    }

    @Override
    public ServiceResponseDto updateSurveyStatus(PersonaEncuestaDto personaEncuestaDto) {
        return null;
    }

    @Override
    public ServiceResponseDto listSurveyPersonByIdPersonWhenStatusFalse(String idSurveyPerson) {
        return null;
    }

    @Override
    public ServiceResponseDto listSurveyPersonByIdPersonWhenStatusTrue(String idSurveyPerson) {
        return null;
    }

//    @Override
//    public ServiceResponseDto listSurveyPersonByIdPersonWhenStatusFalse(String idSurveyPerson) {
//        int statusCode = Constants.SUCCESS_STATUS_CODE;
//        Map<String, Object> data =  new LinkedHashMap<String, Object>();
//        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
//        try {
//            List<PersonaEncuesta> surveyPeople = personaEncuestaRespository.listSurveyPersonByIdPersonWhenStatusFalse(idSurveyPerson);
//            if (surveyPeople != null) {
//                List<PersonaEncuestaDto> surveyPeopleDto = surveyPeople.stream()
//                        .map(PersonaEncuestaDto::ConvertSurveyPersonToSurveyPersonDto)
//                        .collect(Collectors.toList());
//                data.put("objects", surveyPeopleDto);
//                data.put("total", surveyPeople.size());
//            } else {
//                serviceResponseDto.setInfo("No se encuentran registros en PersonaEncuesta a listar!",
//                        HttpStatus.NOT_FOUND.value(), Constants.MESSAGE);
//            }
//        }
//            catch (Exception e){
//                statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
//                data.put("message", "Error al listar PersonaEncuesta");
//                log.error("ListSurveyPerson failed", e);
//            }
//        serviceResponseDto.setStatusCode(statusCode);
//        serviceResponseDto.setData(data);
//        return serviceResponseDto;
//    }

//    @Override
//    public ServiceResponseDto listSurveyPersonByIdPersonWhenStatusTrue(String idSurveyPerson) {
//        int statusCode = Constants.SUCCESS_STATUS_CODE;
//        Map<String, Object> data =  new LinkedHashMap<String, Object>();
//        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
//        try {
//            List<PersonaEncuesta> surveyPeople = personaEncuestaRespository.listSurveyPersonByIdPersonWhenStatusTrue(idSurveyPerson);
//            if (surveyPeople != null) {
//                List<PersonaEncuestaDto> surveyPeopleDto = surveyPeople.stream()
//                        .map(PersonaEncuestaDto::ConvertSurveyPersonToSurveyPersonDto)
//                        .collect(Collectors.toList());
//                data.put("objects", surveyPeopleDto);
//                data.put("total", surveyPeople.size());
//            } else {
//                serviceResponseDto.setInfo("No se encuentran registros en PersonaEncuesta a listar!",
//                        HttpStatus.NOT_FOUND.value(), Constants.MESSAGE);
//            }
//        }
//        catch (Exception e){
//            statusCode = Constants.INTERNAL_SERVER_ERROR_STATUS_CODE;
//            data.put("message", "Error al listar PersonaEncuesta");
//            log.error("ListSurveyPerson failed", e);
//        }
//        serviceResponseDto.setStatusCode(statusCode);
//        serviceResponseDto.setData(data);
//        return serviceResponseDto;
//    }
}