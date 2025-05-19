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
    @NotBlank(message = "El costo no puede estar vacio")
    private Integer costoInscripcion;

    @Column(nullable = false)
    @NotBlank(message = "El campo id-alumno no puede ser vacio")
    private Long idAlumno;
}
