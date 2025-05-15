package com.msvc_notas.Models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Inscripcion {

    private Long idInscripcion;
    private String nombreInscripcion;
    private Integer costoInscripcion;
}
