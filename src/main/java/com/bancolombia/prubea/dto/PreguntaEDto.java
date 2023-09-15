package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.entity.TipoPregunta;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class PreguntaEDto {
    @ApiModelProperty(value = "", example = "1")
    private String idQuestion;
    @ApiModelProperty(value = "Posicion de la pregunta en la encuesta?", example = "1")
    private Integer indexPregunta;
    @ApiModelProperty(value = "Haz una pregunta", example = "Cual es el apellido de Goku?")
    private String dsQuestion;
    @ApiModelProperty(example = "Muchas Opciones")
    private String options;
    private TipoPreguntaDto questionTypeDto;
//    @ApiModelProperty(hidden = true) // Ocultar el parametros Answer para el crear que es el que lo usa
    private Set<RespuestaDto> answerDto;
//    private EncuestaDto surveyDto; // permite mostrar la encuesta desde la preguntaDto

    public PreguntaE convertQuestionsDtoToQuestions(PreguntaEDto questionEDto, Encuesta encuesta, TipoPregunta questionType) {
        return PreguntaE.builder()
                .idPregunta(UUID.randomUUID().toString())
                .tipoPregunta(questionType)
                .indexPregunta(questionEDto.getIndexPregunta())
                .dsPregunta(questionEDto.getDsQuestion())
                .opciones(questionEDto.getOptions())
                .encuesta(encuesta)
                .build();
    }

    public static PreguntaEDto convertQuestionsToQuestionsDtoWithoutAnswers(PreguntaE questionE) {
        return PreguntaEDto.builder()
                .questionTypeDto(TipoPreguntaDto.convertQuestionTypeToQuestionTypeDto(questionE.getTipoPregunta()))
                .idQuestion(questionE.getIdPregunta())
                .indexPregunta(questionE.getIndexPregunta())
                .dsQuestion(questionE.getDsPregunta())
                .options(questionE.getOpciones())
                .build();
    }

    public static PreguntaEDto convertQuestionsToQuestionsDtoWithAnswers(PreguntaE questionE) {
        return PreguntaEDto.builder()
                .questionTypeDto(TipoPreguntaDto.convertQuestionTypeToQuestionTypeDto(questionE.getTipoPregunta()))
                .idQuestion(questionE.getIdPregunta())
                .indexPregunta(questionE.getIndexPregunta())
                .dsQuestion(questionE.getDsPregunta())
                .options(questionE.getOpciones())
                .answerDto(questionE.getRespuestas().stream().map(RespuestaDto::convertAnswerToAnswerDto).collect(Collectors.toSet()))
//                .surveyDto(EncuestaDto.convertSurveyToSurveyDto(questionE.getEncuesta())) // mostrar encuesta desde pregunta
                .build();
    }
}
