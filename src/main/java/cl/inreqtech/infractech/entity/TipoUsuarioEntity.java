package cl.inreqtech.infractech.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TipoUsuario")
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
}
