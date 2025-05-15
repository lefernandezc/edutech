package com.msvc_inscripcion.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name= "INSCRIPCION")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor

public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_inscripcion")
    private Long idInscripcion;

    @Column(nullable = false)
    @NotBlank(message = "El nombre de la asignatura no debe estar vacio")
    private String nombreInscripcion;

    @Column(nullable = false)
    @NotBlank(message = "El costo no debe estar vacio")
    private Integer costoInscripcion;
}
