package cl.inreqtech.infractech.controller;

import cl.inreqtech.infractech.model.Usuario;
import cl.inreqtech.infractech.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuarioLogin) {
        return new ResponseEntity<>(loginService.login(usuarioLogin), HttpStatus.OK);
    }
}
