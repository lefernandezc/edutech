package com.msvc_alumno.model;


import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Curso {

    private Long idCurso;
    private Integer costoCurso;
    private Long idAlumno;

}
