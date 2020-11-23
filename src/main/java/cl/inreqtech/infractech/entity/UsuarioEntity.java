package cl.inreqtech.infractech.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idTipoUsuario;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String rut;

    private String pass;
}
