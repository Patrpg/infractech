package cl.inreqtech.infractech.controller;

import cl.inreqtech.infractech.model.Usuario;
import cl.inreqtech.infractech.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        return new ResponseEntity<>(usuarioService.getUsuarioById(id), HttpStatus.OK);
    }

    @PostMapping("/usuario/{id}")
    public ResponseEntity<Usuario> editusuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.updateUsuario(id, usuario), HttpStatus.OK);
    }

}
