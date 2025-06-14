package com.msvc_profesor.models;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Alumno {

    private Long idAlumno;
    private String run;
    private String nombre;
    private String correo;
    private Long idNotas;
    private Long idInscripcion;
    private Long idProfesor;

}
