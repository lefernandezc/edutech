package com.msvc_profesor.dtos;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ProfesorDTO {

    private String run;
    private String nombre;
    private String correo;
    private String asignatura;
}
