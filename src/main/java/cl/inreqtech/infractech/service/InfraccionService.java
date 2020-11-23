package cl.inreqtech.infractech.service;


import cl.inreqtech.infractech.entity.InfraccionEntity;
import cl.inreqtech.infractech.model.Infraccion;
import cl.inreqtech.infractech.repository.InfraccionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class InfraccionService {

    private final InfraccionRepository infraccionRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public InfraccionService(InfraccionRepository infraccionRepository, UsuarioService usuarioService) {
        this.infraccionRepository = infraccionRepository;
        this.usuarioService = usuarioService;
    }


    public List<Infraccion> getAllInfracciones() {
        List<Infraccion> ret = new ArrayList<>();
        List<InfraccionEntity> infraccionEntities = infraccionRepository.findAll();
        for (InfraccionEntity infraccionEntity : infraccionEntities) {
            ret.add(getFromEntity(infraccionEntity));
        }
        return ret;
    }

    public List<Infraccion> getInfraccionesByRut(String rut) {
        List<Infraccion> ret = new ArrayList<>();
        List<InfraccionEntity> infraccionEntities = infraccionRepository.findAllByRutUsuario(rut);
        for (InfraccionEntity infraccionEntity : infraccionEntities) {
            ret.add(getFromEntity(infraccionEntity));
        }
        return ret;
    }

    public Infraccion getInfraccionById(Integer id) {
        Optional<InfraccionEntity> ie = infraccionRepository.findById(id);
        if (ie.isPresent()) {
            return getFromEntity(ie.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe infraccion con ese id");
    }

    public Infraccion crearInfraccion(Infraccion infraccion) {
        if (infraccion.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La infraccion a crear no puede tener id");
        }
        InfraccionEntity infraccionEntity = getFromModel(infraccion);
        infraccionEntity = infraccionRepository.save(infraccionEntity);
        infraccion.setId(infraccionEntity.getId());
        return infraccion;
    }

    public Infraccion updateInfraccion(Integer id, Infraccion infraccion) {
        if (!infraccion.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La infraccion a actualizar debe tener la id del path");
        }

        if (infraccion.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La infraccion a actualizar debe tener id");
        }

        InfraccionEntity infraccionEntity = getFromModel(infraccion);
        infraccionRepository.save(infraccionEntity);
        return infraccion;
    }

    public Infraccion getFromEntity(InfraccionEntity infraccionEntity) {
        Infraccion infraccion = new Infraccion();
        infraccion.setId(infraccionEntity.getId());
        infraccion.setUsuario(usuarioService.getUsuarioById(infraccionEntity.getIdUsuario()));
        infraccion.setPlacaPatente(infraccionEntity.getPlacaPatente());
        infraccion.setMonto(infraccionEntity.getMonto());
        infraccion.setObservacion(infraccionEntity.getObservacion());
        infraccion.setFechaEmision(infraccionEntity.getFechaEmision());
        infraccion.setFechaPago(infraccionEntity.getFechaPago());
        return infraccion;
    }

    public InfraccionEntity getFromModel(Infraccion infraccion) {
        InfraccionEntity infraccionEntity = new InfraccionEntity();
        infraccionEntity.setId(infraccion.getId());
        infraccionEntity.setIdUsuario(infraccion.getUsuario().getId());
        infraccionEntity.setPlacaPatente(infraccion.getPlacaPatente());
        infraccionEntity.setMonto(infraccion.getMonto());
        infraccionEntity.setObservacion(infraccion.getObservacion());
        infraccionEntity.setFechaEmision(infraccion.getFechaEmision());
        infraccion.setFechaPago(infraccion.getFechaPago());
        return infraccionEntity;
    }
}
