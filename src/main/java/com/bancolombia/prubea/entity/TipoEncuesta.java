package com.bancolombia.prubea.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "TIPO_ENCUESTA")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoEncuesta {

    @Id
    @Column(name = "id_tipo_encuesta", unique = true, nullable = false, length = 16)
    public String idTipoEncuesta;

    @Column(name = "ds_tipo_encuesta", unique = true, nullable = false)
    public String dsTipoEncuesta;

    @OneToMany(mappedBy = "tipoEncuesta", fetch = FetchType.LAZY)
    private Set<Encuesta> encuestas;

}