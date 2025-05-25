package com.msvc_inscripcion.Models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {

    private Long idProfesor;
    private String run;
    private String nombre;
    private String correo;
    private String asignatura;
    private Long idAlumno;
    private Long idNotas;
}
