package cl.inreqtech.infractech.model;

import lombok.Data;

@Data
public class Usuario {

    private Integer id;

    private TipoUsuario tipoUsuario;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String rut;

    private String pass;
}
