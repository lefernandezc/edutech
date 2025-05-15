package com.msvc_inscripcion.Models;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Alumno {

    private Long idAlumnno;
    private String run;
    private String nombre;
    private String correo;
}
