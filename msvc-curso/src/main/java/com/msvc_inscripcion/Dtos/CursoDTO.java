package com.msvc_inscripcion.Dtos;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Integer costoInscripcion;
    private AlumnoDTO alumno;
}
