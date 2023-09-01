package com.bancolombia.prubea.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TIPO_PREGUNTA")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
@Builder
public class TipoPregunta  {

    @Id
    @Column(name = "id_tipo_pregunta", unique = true, nullable = false, length = 100)
    private String idTipoPregunta;

    @Column(name = "ds_tipo_pregunta", unique = true, nullable = false)
    private String dsTipoPregunta;

    @OneToMany(mappedBy = "tipoPregunta", fetch = FetchType.LAZY)
    private Set<PreguntaE> preguntaES;

}