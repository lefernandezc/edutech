package com.msvc_notas.Dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotasDTO {

    private Integer nota;
    private AlumnoDTO alumno;
    private ProfesorDTO profesor;
}
