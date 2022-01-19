package com.ceiduns.app.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Curso {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private Long id;

    @Column(length = 20)
    @NotBlank(message = "El idioma no debe estar en blanco")
    private String idioma;

    @Column(length = 20)
    @NotBlank(message = "El nivel no debe estar en blanco")
    private String nivel;

    @Column(length = 5)
    @NotBlank(message = "El ciclo no debe estar en blanco")
    private String ciclo;

    @NotNull
    @Min(value = 0)
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "horario_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_horario_curso"))
    private Horario horario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "aula_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_aula_curso"))
    private Aula aula;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    private List<Matricula> matriculas;
}
