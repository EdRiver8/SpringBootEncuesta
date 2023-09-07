package com.bancolombia.prubea.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Table(name ="PERSONA_ENCUESTA")
public class PersonaEncuesta {

    @Id
    @Column (name = "id_persona_encuesta", length = 36)
    private String idPersonaEncuesta;

    @Column (name = "es_respuesta", length = 36)
    private Boolean esRespuesta;

    @Column (name = "referencia", length = 36)
    private String referencia;

    @Column(name = "tipo_referencia")
    private String tipoReferencia;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_persona")
    private Persona persona;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_encuesta")
    private Encuesta encuesta;

    @OneToMany(mappedBy = "personaEncuesta", fetch = FetchType.LAZY)
    private Set<Respuesta> respuestas;
}
