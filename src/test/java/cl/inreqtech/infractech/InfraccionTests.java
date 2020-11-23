package cl.inreqtech.infractech;

import cl.inreqtech.infractech.controller.InfraccionController;
import cl.inreqtech.infractech.entity.UsuarioEntity;
import cl.inreqtech.infractech.model.Infraccion;
import cl.inreqtech.infractech.model.Usuario;
import cl.inreqtech.infractech.repository.UsuarioRepository;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InfraccionTests {

    @Autowired
    InfraccionController infraccionController;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @Order(1)
    void getInfracciones() {
        ResponseEntity<List<Infraccion>> response = infraccionController.getAllInfracciones();
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void insertInfraccion() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdTipoUsuario(1);
        usuarioEntity.setRut("19278209-0");
        usuarioEntity.setNombres("Patricio Leandro");
        usuarioEntity.setApellidoPaterno("Pino");
        usuarioEntity.setApellidoMaterno("Gonzalez");
        usuarioEntity.setPass("asd");
        usuarioRepository.save(usuarioEntity);

        Infraccion infraccion = new Infraccion();
        infraccion.setUsuario(new Usuario());
        infraccion.getUsuario().setId(1);
        infraccion.setPlacaPatente("XX-XX-XX");
        infraccion.setMonto(40000);
        infraccion.setFechaEmision(LocalDateTime.now());
        infraccion.setObservacion("Exceso de velocidad.");

        ResponseEntity<Infraccion> response = infraccionController.createInfraccion(infraccion);
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(3)
    void getInfraccionById() {
        ResponseEntity<Infraccion> response = infraccionController.getInfraccionById(1);
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(4)
    void getInfraccionesByRut() {
        ResponseEntity<List<Infraccion>> response = infraccionController.getInfraccionesByRut("19278209-0");
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(5)
    void updateInfraccion() {
        Infraccion infraccion = new Infraccion();
        infraccion.setId(1);
        infraccion.setUsuario(new Usuario());
        infraccion.getUsuario().setId(1);
        infraccion.setPlacaPatente("XX-XX-XX");
        infraccion.setMonto(40000);
        infraccion.setFechaEmision(LocalDateTime.now());
        infraccion.setObservacion("Exceso de velocidad.");
        infraccion.setFechaPago(LocalDateTime.now());

        ResponseEntity<Infraccion> response = infraccionController.editInfraccion(1, infraccion);
        log.info(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
