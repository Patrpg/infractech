package cl.inreqtech.infractech.controller;

import cl.inreqtech.infractech.model.Infraccion;
import cl.inreqtech.infractech.service.InfraccionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
public class InfraccionController {

    private final InfraccionService infraccionService;

    @Autowired
    public InfraccionController(InfraccionService infraccionService) {
        this.infraccionService = infraccionService;
    }

    @GetMapping("/infraccion")
    public ResponseEntity<List<Infraccion>> getAllInfracciones() {
        return new ResponseEntity<>(infraccionService.getAllInfracciones(), HttpStatus.OK);
    }

    @PostMapping("/infraccion")
    public ResponseEntity<Infraccion> createInfraccion(@RequestBody Infraccion infraccion) {
        return new ResponseEntity<>(infraccionService.crearInfraccion(infraccion), HttpStatus.OK);
    }

    @GetMapping("/infraccion/{id}")
    public ResponseEntity<Infraccion> getInfraccionById(@PathVariable Integer id) {
        return new ResponseEntity<>(infraccionService.getInfraccionById(id), HttpStatus.OK);
    }

    @PostMapping("/infraccion/{id}")
    public ResponseEntity<Infraccion> editInfraccion(@PathVariable Integer id, @RequestBody Infraccion infraccion) {
        return new ResponseEntity<>(infraccionService.updateInfraccion(id, infraccion), HttpStatus.OK);
    }

    @GetMapping("/usuario/{rut}/infraccion")
    public ResponseEntity<List<Infraccion>> getInfraccionesByRut(@PathVariable String rut) {
        return new ResponseEntity<>(infraccionService.getInfraccionesByRut(rut), HttpStatus.OK);
    }

}
