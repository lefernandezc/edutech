package com.msvc_notas.Models.Entities;

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
