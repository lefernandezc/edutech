package com.msvc_alumno.dtos;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
    private String run;
    private String correo;
    private String nombre;
    private InscripcionDTO inscripcion;

    public Long getIdAlumno() {
        String run;
        String correo;
        String nombre;
        InscripcionDTO inscripcion;
        return 0L;
    }
}
