package com.msvc_alumno.model.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "alumno")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long idAlumno;

    @Column(name = "run")
    @NotBlank(message = "El run de alumno no puede esta vacio")
    private String run;

    @Column(name = "nombre")
    @NotBlank(message = "El nombre de alumno no puede esta vacio")
    private String nombre;

    @Column(name = "correo")
    @NotBlank(message = "El correo del alumno no puede esta vacio")
    private String correo;


    @Column(name = "id_inscripcion", nullable = false)
    @NotNull(message = "El campo de id_inscripcion no puede estar vacio")
    private Long idCurso;



}
