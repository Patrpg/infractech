package cl.inreqtech.infractech.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Infraccion")
public class InfraccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idUsuario;

    private String placaPatente;

    private Integer monto;

    private String observacion;

    private LocalDateTime fechaEmision;

    private LocalDateTime fechaPago;
}
