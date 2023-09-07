package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.entity.TipoPregunta;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoPreguntaDto {
    @ApiModelProperty(example = "1")
    private String idQuestionType;
    @ApiModelProperty(example = "Opcion multiple, unica respuesta")
    private String dsQuestionType;

    public static TipoPregunta convertQuestionTypeDtoToQuestionType
            (TipoPreguntaDto questionTypeDto){
        return TipoPregunta.builder()
                .idTipoPregunta(questionTypeDto.getIdQuestionType())
                .build();
    }

    public static TipoPreguntaDto convertQuestionTypeToQuestionTypeDto(TipoPregunta questionType){
        return TipoPreguntaDto.builder()
                .idQuestionType(questionType.getIdTipoPregunta())
                .dsQuestionType(questionType.getDsTipoPregunta())
                .build();
    }

}
