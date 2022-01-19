package com.ceiduns.app.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class Aula {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private Long id;

    @Column(length = 15)
    @NotBlank(message = "El nombre del aula es requerido")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aula")
    private List<Curso> cursos;
}
