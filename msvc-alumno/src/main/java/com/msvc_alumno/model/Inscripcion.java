package com.msvc_alumno.model;


import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Inscripcion {

    private Long idInscripcion;
    private String nombreAsignatura;
    private Integer costo;
}
