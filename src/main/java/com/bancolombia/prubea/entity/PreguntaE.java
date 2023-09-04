package com.bancolombia.prubea.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PREGUNTAE")
@Getter @Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encuesta")
    private Encuesta encuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pregunta")
    private TipoPregunta tipoPregunta;

    @OneToOne(mappedBy = "pregunta", fetch = FetchType.LAZY)
    private Respuesta respuesta;

//    @OneToMany(mappedBy = "pregunta", fetch = FetchType.LAZY)
//    private Set<Respuesta> respuestas;

//    @Column(name = "id_encuesta")
//    private String idEncuesta;
//
//    @Column(name = "id_tipo_pregunta")
//    private String idTipoPregunta;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta", insertable = false, updatable = false)
//    private Encuesta encuesta;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_tipo_pregunta", referencedColumnName = "id_tipo_pregunta", insertable = false, updatable = false)
//    private TipoPregunta tipoPregunta;

}
