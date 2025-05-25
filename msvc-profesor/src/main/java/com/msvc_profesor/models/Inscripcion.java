package com.msvc_profesor.models;

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
