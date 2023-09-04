package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.entity.Respuesta;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RespuestaDto {
    @ApiModelProperty(example = "hahahahaha")
    private String idAnswer;
    @ApiModelProperty(example = "Esta es una respuesta")
    private String answer;
    @ApiModelProperty(example = "Natalia6675677678")
    private String idPerson;
    @ApiModelProperty(example = "CU01")
    private String reference;

    public static Respuesta convertAnswerDtoToAnswer(RespuestaDto answerDto, PreguntaE question){
        return Respuesta.builder()
                .idRespuesta(UUID.randomUUID().toString())
                .respuesta(answerDto.getAnswer())
                .idPersona(answerDto.idPerson)
                .referencia(answerDto.getReference())
                .pregunta(question)
                .build();
    }

    public static RespuestaDto convertAnswerToAnswerDto(Respuesta answerQ){
        return RespuestaDto.builder()
                .idAnswer(answerQ.getIdRespuesta())
                .answer(answerQ.getRespuesta())
                .idPerson(answerQ.getIdPersona())
                .reference(answerQ.getReferencia())
                .build();
    }

}
