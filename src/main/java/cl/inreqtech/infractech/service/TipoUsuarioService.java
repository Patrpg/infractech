package cl.inreqtech.infractech.service;

import cl.inreqtech.infractech.entity.TipoUsuarioEntity;
import cl.inreqtech.infractech.model.TipoUsuario;
import cl.inreqtech.infractech.repository.TipoUsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Log4j2
@Service
public class TipoUsuarioService {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario getTipoUsuarioFromId(Integer id) {
        Optional<TipoUsuarioEntity> ore = tipoUsuarioRepository.findById(id);
        if (ore.isPresent()) {
            return getTipoUsuarioFromEntity(ore.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de usuario no encontrado en base de datos");
        }
    }

    public TipoUsuario getTipoUsuarioFromEntity(TipoUsuarioEntity rolEntity) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(rolEntity.getId());
        tipoUsuario.setNombre(rolEntity.getNombre());
        return tipoUsuario;
    }
}
