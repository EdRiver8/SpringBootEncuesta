package com.bancolombia.prubea.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Column(name = "id_persona")
    private String idPersona;
    private String referencia;

//    @Column(name = "id_pregunta")
//    private String pregunta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta")
    private PreguntaE pregunta;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_pregunta")
//    private PreguntaE pregunta;

}
