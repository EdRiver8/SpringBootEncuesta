package com.bancolombia.prubea.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "RESPUESTA")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Respuesta {

    @Id
    @Column(name = "id_respuesta")
    private String idRespuesta;

    private String respuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona_encuesta")
    private PersonaEncuesta personaEncuesta;

//    @Column(name = "id_pregunta")
//    private String pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta")
    private PreguntaE pregunta;

}
