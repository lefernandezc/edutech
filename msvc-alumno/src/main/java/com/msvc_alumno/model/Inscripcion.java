package com.msvc_alumno.model;


import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Inscripcion {

    private Long idInscripcion;
    private Integer costoInscripcion;
    private Long idAlumno;

}
