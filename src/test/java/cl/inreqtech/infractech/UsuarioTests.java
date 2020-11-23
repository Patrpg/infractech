package cl.inreqtech.infractech;

import cl.inreqtech.infractech.controller.UsuarioController;
import cl.inreqtech.infractech.model.TipoUsuario;
import cl.inreqtech.infractech.model.Usuario;
import cl.inreqtech.infractech.repository.TipoUsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioTests {

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @Test
    @Order(1)
    void getUsuarios() {
        ResponseEntity<List<Usuario>> response = usuarioController.getAllUsuarios();
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void insertUsuario() {
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(new TipoUsuario());
        usuario.getTipoUsuario().setId(1);
        usuario.setRut("19278209-0");
        usuario.setNombres("Patricio Leandro");
        usuario.setApellidoPaterno("Pino");
        usuario.setApellidoMaterno("Gonzalez");
        usuario.setPass("asd");
        ResponseEntity<Usuario> response = usuarioController.createUsuario(usuario);
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(3)
    void getUsuarioById() {
        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1);
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(4)
    void updateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setTipoUsuario(new TipoUsuario());
        usuario.getTipoUsuario().setId(1);
        usuario.setRut("19278209-0");
        usuario.setNombres("Patricio");
        usuario.setApellidoPaterno("Pino");
        usuario.setApellidoMaterno("Gonzalez");
        usuario.setPass("asd");

        ResponseEntity<Usuario> response = usuarioController.editusuario(1, usuario);
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
