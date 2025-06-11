package com.msvc_inscripcion.Models.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name= "INSCRIPCION")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Entidad que representa una inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_inscripcion")
    @Schema(description = "La primary key de inscripcion", example = "1")
    private Long idInscripcion;

    @Column(nullable = false)
    @NotNull(message = "El costo no puede estar vacio")
    @Schema(description = "El costo de la inscripcion", example = "20000")
    private Integer costoInscripcion;

}
