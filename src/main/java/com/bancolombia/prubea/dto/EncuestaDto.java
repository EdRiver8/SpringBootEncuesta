package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.Encuesta;
import com.bancolombia.prubea.entity.TipoEncuesta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ApiModel(description = "Modelado de la Encuesta")
public class EncuestaDto {
    private String idSurvey;
    @ApiModelProperty(example = "Encuesta Curso...")
    private String surveyName;
    @ApiModelProperty(example = "Encuesta dirigida al curso de Spring")
    private String dsSurvey;
    @ApiModelProperty(example = "Activada")
    private String state;
    @ApiModelProperty(value = "1", example = "3")
    private Integer amountQuestions;
    private TipoEncuestaDto surveyTypeDto;
    private Set<PreguntaEDto> questionDto;


    // Request -> Usuario hacia la DB, Dto y convertirlo a Entidad
    public Encuesta convertSurveyDtoToSurvey(EncuestaDto surveyDto, TipoEncuesta surveyType) {
        return Encuesta.builder()
                .idEncuesta(UUID.randomUUID().toString())
                .nombreEncuesta(surveyDto.getSurveyName())
                .dsEncuesta(surveyDto.getDsSurvey())
                .cantidadPreguntas(surveyDto.getAmountQuestions())
                .esEncuesta(surveyDto.getState())
                .tipoEncuesta(surveyType)
                .build();
    }

    // Response -> DB hacia el usuario, entidad y convertirla a DTO
    public static EncuestaDto convertSurveyToSurveyDto(Encuesta survey) {
        return EncuestaDto.builder()
                .idSurvey(survey.getIdEncuesta())
                .surveyName(survey.getNombreEncuesta())
                .dsSurvey(survey.getDsEncuesta())
                .amountQuestions(survey.getCantidadPreguntas())
                .state(survey.getEsEncuesta())
                .surveyTypeDto(TipoEncuestaDto.convertSurveyTypeToSurveyTypeDto(survey.getTipoEncuesta())) // permite mostrar el tipo desde encuesta
//                .questionDto(survey.getPreguntas().stream().map(PreguntaEDto::convertQuestionSToQuestionSDto) // permite mostrar las preguntas desde encuesta
//                        .collect(Collectors.toSet()))
                .build();
    }

}
