package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.Persona;
import com.bancolombia.prubea.entity.PersonaEncuesta;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PersonaEncuestaDto {
    private String idSurveyPerson;
    private Boolean surveyStatus;
    private String reference;
    private String idPerson;
    private EncuestaDto idSurveyDto;

    public PersonaEncuesta ConvertSurveyPersonDtoToSurveyPerson(PersonaEncuestaDto personaEncuestaDto, Persona persona, Encuesta encuesta)
    {
        return PersonaEncuesta.builder()
                .idPersonaEncuesta(personaEncuestaDto.getIdSurveyPerson())
                .esRespuesta(personaEncuestaDto.getSurveyStatus())
                .referencia(personaEncuestaDto.getReference())
                .persona(persona)
                .encuesta(encuesta)
                .build();
    }

    public static PersonaEncuestaDto ConvertSurveyPersonToSurveyPersonDto(PersonaEncuesta personaEncuesta)
    {
        return PersonaEncuestaDto.builder()
                .idSurveyPerson(personaEncuesta.getIdPersonaEncuesta())
                .surveyStatus(personaEncuesta.getEsRespuesta())
                .reference(personaEncuesta.getReferencia())
                .idPerson(personaEncuesta.getIdPersonaEncuesta())
                .idSurveyDto(EncuestaDto.convertSurveyToSurveyDto(personaEncuesta.getEncuesta()))
                .build();

    }

}
