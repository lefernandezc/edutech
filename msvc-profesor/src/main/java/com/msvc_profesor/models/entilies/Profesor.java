package com.msvc_profesor.models.entilies;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "profesor")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long idProfesor;

    @Column
    @NotBlank(message = "El run no puede estar vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dkk]", message = "El run no puede estar vacio")
    private String run;

    @Column
    @NotBlank(message = "El nombre del profesor no pude estar vacio")
    private String nombre;

    @Column
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;

    @Column
    @NotBlank(message = "La asignatura no puede estar vacio")
    private String asignatura;

    @Column(name = "id_alumno", nullable = false)
    @NotNull(message = "El mensaje de ide_notas no puede estar vacio")
    private Long idAlumno;

    @Column(name = "id_notas", nullable = false)
    @NotNull(message = "El mensaje de ide_notas no puede estar vacio")
    private Long idNotas;

}
