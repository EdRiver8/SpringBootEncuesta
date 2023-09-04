package com.bancolombia.prubea.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ENCUESTA")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Encuesta {
    @Id
    @Column(name = "id_encuesta", length = 36)
    private String idEncuesta;

    @Column(name = "nombre_encuesta", nullable = false, length = 36)
    private String nombreEncuesta;

    @Column(name = "ds_encuesta",length = 100)
    private String dsEncuesta;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cantidad_preguntas")
//    @Min(value = 1, message = "Ingrese minimo una pregunta") // spring-boot-starter-validation
    private Integer cantidadPreguntas;

    @OneToMany(mappedBy = "encuesta", fetch = FetchType.LAZY)
    private Set<PreguntaE> preguntas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_encuesta")
    private TipoEncuesta tipoEncuesta;



//    @Column(name = "id_tipo_encuesta")
//    private String idTipoEncuesta;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_tipo_encuesta", referencedColumnName = "id_tipo_encuesta", updatable = false, insertable = false)
//    private TipoEncuesta tipoEncuesta;

}
