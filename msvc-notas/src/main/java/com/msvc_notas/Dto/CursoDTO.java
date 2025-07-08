package com.msvc_notas.Dto;


import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private String asignatura;
    private ProfesorDTO profesor;

    public Long getIdCurso() {
        String asignatura;
        ProfesorDTO profesor;
        return 0L;
    }
}
