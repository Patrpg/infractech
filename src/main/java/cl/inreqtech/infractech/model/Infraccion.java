package cl.inreqtech.infractech.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Infraccion {

    private Integer id;

    private Usuario usuario;

    private String placaPatente;

    private Integer monto;

    private String observacion;

    private LocalDateTime fechaEmision;

    private LocalDateTime fechaPago;
}
