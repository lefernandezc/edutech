package com.msvc_notas.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO alumno")
public class NotasDTO {

    @JsonIgnore
    private Long idNotas;

    @Schema(description = "nota alumno", example = "7")
    private Integer nota;

    @Schema(description = "Este es el alumno con la nota",
            implementation = AlumnoDTO.class)
    private AlumnoDTO alumno;

    @Schema(description = "Este es el alumno con la nota",
            implementation = ProfesorDTO.class)
    private ProfesorDTO profesor;
}
