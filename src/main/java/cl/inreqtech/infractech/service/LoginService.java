package cl.inreqtech.infractech.service;

import cl.inreqtech.infractech.model.Usuario;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@Service
public class LoginService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario login(Usuario usuarioLogin) {
        Usuario usuario = usuarioService.getUsuarioByRut(usuarioLogin.getRut());
        if (passwordEncoder.matches(usuarioLogin.getPass(), usuario.getPass())) {
            return usuarioLogin;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Pass inválida");
        }
    }
}
