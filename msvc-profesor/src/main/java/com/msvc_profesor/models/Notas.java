package com.msvc_profesor.models;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Notas {

    private Long idNotas;
    private Integer nota;
    private Long idAlumno;
    private Long idProfesor;
}
