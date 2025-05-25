package com.msvc_alumno.model;


import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Notas {

    private Long idNotas;
    private Integer nota;
    private Long idAlumno;
}
