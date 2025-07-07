package com.msvc_inscripcion.Models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

    private Long idProfesor;
    private String run;
    private String nombre;
    private String correo;
}
