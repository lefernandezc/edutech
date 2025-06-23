package com.msvc_notas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
    private String run;
    private String correo;
    private String nombre;
}
