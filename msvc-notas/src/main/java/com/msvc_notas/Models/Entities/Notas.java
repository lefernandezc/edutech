package com.msvc_notas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name= "NOTAS")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor

public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_notas")
    private Long idNotas;

    @Column(nullable = false)
    @NotNull(message = "La nota no puede estar vacio")
    private Integer nota;

    @Column(nullable = false)
    @NotNull(message = "El campo id-alumno no puede ser vacio")
    private Long idAlumno;

    @Column(nullable = false)
    @NotNull(message = "El campo id-curso no puede ser vacio")
    private Long idCurso;
}
