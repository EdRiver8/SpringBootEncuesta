package com.bancolombia.prubea.entity;

import lombok.*;

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
    private String id;

    private String nombre;

    private String oferta;

}
