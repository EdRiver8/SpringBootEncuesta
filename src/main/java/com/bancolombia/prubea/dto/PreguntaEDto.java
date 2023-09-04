package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.entity.TipoPregunta;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PreguntaEDto {
    @ApiModelProperty(value = "", example = "hahahahaha")
    private String idQuestion;
    @ApiModelProperty(value = "Posicion de la pregunta en la encuesta?", example = "1")
    private Integer indexPregunta;
    @ApiModelProperty(value = "Haz una pregunta", example = "Cual es el apellido de Goku?")
    private String dsQuestion;
    @ApiModelProperty(example = "Muchas Opciones")
    private String options;
    private TipoPreguntaDto questionTypeDto;
    private RespuestaDto answerDto;
//    private EncuestaDto surveyDto; // permite mostrar la encuesta desde la preguntaDto

    public PreguntaE convertQuestionSDtoToQuestionS(PreguntaEDto questionEDto, Encuesta encuesta, TipoPregunta questionType) {
        return PreguntaE.builder()
                .idPregunta(UUID.randomUUID().toString())
                .tipoPregunta(questionType)
                .indexPregunta(questionEDto.getIndexPregunta())
                .dsPregunta(questionEDto.getDsQuestion())
                .opciones(questionEDto.getOptions())
                .encuesta(encuesta)
                .build();
    }

    public static PreguntaEDto convertQuestionSToQuestionSDto(PreguntaE questionE) {
        return PreguntaEDto.builder()
                .questionTypeDto(TipoPreguntaDto.convertQuestionTypeToQuestionTypeDto(questionE.getTipoPregunta()))
                .idQuestion(questionE.getIdPregunta())
                .indexPregunta(questionE.getIndexPregunta())
                .dsQuestion(questionE.getDsPregunta())
                .options(questionE.getOpciones())
                .answerDto(RespuestaDto.convertAnswerToAnswerDto(questionE.getRespuesta()))
//                .surveyDto(EncuestaDto.convertSurveyToSurveyDto(questionE.getEncuesta())) // permite mostrar la encuesta desde la pregunta
                .build();
    }
}
