package com.msvc_notas.Models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Curso {

    private Long idCurso;
    private String asignatura;
    private Long idProfesor;
}
