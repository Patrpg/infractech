package cl.inreqtech.infractech.service;

import cl.inreqtech.infractech.entity.UsuarioEntity;
import cl.inreqtech.infractech.model.Usuario;
import cl.inreqtech.infractech.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UsuarioService {

    private final TipoUsuarioService tipoUsuarioService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(TipoUsuarioService tipoUsuarioService, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.tipoUsuarioService = tipoUsuarioService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> ret = new ArrayList<>();
        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();
        for (UsuarioEntity usuarioEntity : usuarioEntities) {
            ret.add(getFromEntity(usuarioEntity));
        }
        return ret;
    }

    public Usuario getUsuarioByRut(String rut) {
        Optional<UsuarioEntity> opue = usuarioRepository.findByRut(rut);
        if (opue.isPresent()) {
            return getFromEntity(opue.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe usuario con ese rut");
    }

    public Usuario getUsuarioById(Integer id) {
        Optional<UsuarioEntity> opue = usuarioRepository.findById(id);
        if (opue.isPresent()) {
            return getFromEntity(opue.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe usuario con ese id");
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario a crear no puede tener id");
        }
        usuario.setPass(passwordEncoder.encode(usuario.getPass()));
        UsuarioEntity usuarioEntity = getFromModel(usuario);
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        usuario.setId(usuarioEntity.getId());
        return usuario;
    }

    public Usuario updateUsuario(Integer id, Usuario usuario) {
        if (!usuario.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario a actualizar debe tener la id del path");
        }

        if (usuario.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario a actualizar debe tener id");
        }

        usuario.setPass(passwordEncoder.encode(usuario.getPass()));
        UsuarioEntity usuarioEntity = getFromModel(usuario);
        usuarioRepository.save(usuarioEntity);
        return usuario;
    }

    public Usuario getFromEntity(UsuarioEntity usuarioEntity) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioEntity.getId());
        usuario.setTipoUsuario(tipoUsuarioService.getTipoUsuarioFromId(usuarioEntity.getIdTipoUsuario()));
        usuario.setNombres(usuarioEntity.getNombres());
        usuario.setApellidoPaterno(usuarioEntity.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioEntity.getApellidoMaterno());
        usuario.setRut(usuarioEntity.getRut());
        usuario.setPass(usuarioEntity.getPass());
        return usuario;
    }

    public UsuarioEntity getFromModel(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuario.getId());
        usuarioEntity.setIdTipoUsuario(usuario.getTipoUsuario().getId());
        usuarioEntity.setNombres(usuario.getNombres());
        usuarioEntity.setApellidoPaterno(usuario.getApellidoPaterno());
        usuarioEntity.setApellidoMaterno(usuario.getApellidoMaterno());
        usuarioEntity.setRut(usuario.getRut());
        usuarioEntity.setPass(usuario.getPass());
        return usuarioEntity;
    }
}
