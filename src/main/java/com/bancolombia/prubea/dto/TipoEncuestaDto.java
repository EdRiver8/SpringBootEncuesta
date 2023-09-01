package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.TipoEncuesta;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TipoEncuestaDto {
    @ApiModelProperty(example = "1")
    private String idSurveyType;
    @ApiModelProperty(example = "Personalizada")
    private String dsSurveyType;

    public static TipoEncuesta convertSurveyTypeDtoToSurveyType(TipoEncuestaDto tipoEncuestaDto){
        return TipoEncuesta.builder()
                .idTipoEncuesta(tipoEncuestaDto.getIdSurveyType())
                .build();
    }

    public static TipoEncuestaDto convertSurveyTypeToSurveyTypeDto(TipoEncuesta surveyType){
        return TipoEncuestaDto.builder()
                .idSurveyType(surveyType.getIdTipoEncuesta())
                .dsSurveyType(surveyType.getDsTipoEncuesta())
                .build();
    }

}
