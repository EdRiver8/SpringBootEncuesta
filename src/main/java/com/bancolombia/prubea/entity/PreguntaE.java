package com.bancolombia.prubea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PREGUNTAE")
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PreguntaE {
    @Id
    @Column(name = "id_pregunta")
    private String idPregunta;

    @Column(name = "index_pregunta")
    private Integer indexPregunta;

    @Column(name = "ds_pregunta", nullable = false, length = 100)
    private String dsPregunta;

    @Column ( name = "opciones", length = 1000)
    private String opciones;

//    @Column(name = "id_encuesta")
//    private String idEncuesta;

//    @Column(name = "id_tipo_pregunta")
//    private String idTipoPregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encuesta")
    private Encuesta encuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pregunta")
    private TipoPregunta tipoPregunta;
}
