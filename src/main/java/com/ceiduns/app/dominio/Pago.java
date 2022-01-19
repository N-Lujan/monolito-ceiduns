package com.ceiduns.app.dominio;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPago;

    @Column(length = 10)
    @NotBlank(message = "Debe especificar el número de pago")
    private String nroPago;

    @NotNull
    @Min(value = 0)
    private Double monto;

    @Column(length = 100)
    @NotBlank(message = "Especifique el nombre de quien pagó")
    private String titular;

    @Column(length = 8)
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private String dni;

    @Column(length = 30)
    @NotBlank(message = "Debe especificar el tipo de pago")
    private String tipoPago;

    @Column(length = 10)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "alumno_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_alumno_pago"))
    private Alumno alumno;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pago")
    private List<Matricula> matriculas;
}
