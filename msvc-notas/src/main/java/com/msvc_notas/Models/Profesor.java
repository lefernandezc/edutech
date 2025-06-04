package com.msvc_notas.Models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {

    private Long idProfesor;
    private String run;
    private String nombre;
    private String correo;
    private String asignatura;
}
