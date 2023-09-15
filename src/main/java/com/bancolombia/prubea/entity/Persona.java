package com.bancolombia.prubea.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONA")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Persona {

    @Id
    @Column(name = "id_persona")
    private String id;

    @Column(name = "nombre_persona")
    private String nombre;

    private String oferta;

}
