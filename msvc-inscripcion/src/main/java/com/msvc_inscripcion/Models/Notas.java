package com.msvc_inscripcion.Models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Notas {

    private Long idNotas;
    private Integer nota;
    private Long idAlumno;
}
