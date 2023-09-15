package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.PersonaEncuesta;
import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.entity.Respuesta;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class RespuestaDto {
    @ApiModelProperty(example = "1")
    private String idAnswer;
    @ApiModelProperty(example = "Esta es una respuesta")
    private String answer;
    @ApiModelProperty(example = "4")
    private String idPersonSurvey;
    private String idPerson;

    public static Respuesta convertAnswerDtoToAnswer(RespuestaDto answerDto, PreguntaE question){
        PersonaEncuesta personSurvey = new PersonaEncuesta();
        personSurvey.setIdPersonaEncuesta(answerDto.getIdPersonSurvey());
        return Respuesta.builder()
                .idRespuesta(UUID.randomUUID().toString())
                .respuesta(answerDto.getAnswer())
                .personaEncuesta(personSurvey)
                .pregunta(question)
                .build();
    }

    public static RespuestaDto convertAnswerToAnswerDto(Respuesta answerQ){
        return RespuestaDto.builder()
                .idAnswer(answerQ.getIdRespuesta())
                .answer(answerQ.getRespuesta())
                .idPerson(answerQ.getPersonaEncuesta().getPersona().getId())
                .build();
    }

}
