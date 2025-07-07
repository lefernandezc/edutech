package com.msvc_inscripcion.Models.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name= "Curso")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Entidad que representa un curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_curso")
    @Schema(description = "La primary key de curso", example = "1")
    private Long idCurso;

    @Column
    @NotBlank(message = "La asignatura no puede estar vacio")
    @Schema(description = "El nombre de la asignatura", example = "Lenguaje")
    private String asignatura;

    @Column(name = "id_profesor" , nullable = false)
    @NotNull(message = "El campo de id_profesor no puede estar vacio")
    private Long idProfesor;

    public Curso (String asignatura){
        this.asignatura = asignatura;
    }

}
