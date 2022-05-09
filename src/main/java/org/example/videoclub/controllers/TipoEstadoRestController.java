package org.example.videoclub.controllers;

import org.example.videoclub.models.dto.TipoEstadoDTO;
import org.example.videoclub.models.mapper.TipoEstadoMapper;
import org.example.videoclub.services.TipoEstadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoEstado")
public class TipoEstadoRestController {

    Logger log = LoggerFactory.getLogger(TipoEstadoRestController.class);

    @Autowired
    TipoEstadoService tipoEstadoService;

    @Autowired
    TipoEstadoMapper tipoEstadoMapper;

    @GetMapping(value = "/obtenerTiposEstado")
    public ResponseEntity<List<TipoEstadoDTO>> obtenerTiposEstado(){

        return new ResponseEntity<>(tipoEstadoMapper.tipoEstadotoTipoEstadoDTO(tipoEstadoService.obtenerTiposEstado()), HttpStatus.OK);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<Boolean> guardarTipoEstado(
            @RequestBody TipoEstadoDTO tEDTO){

        tipoEstadoService.guardarTipoEstado(tipoEstadoMapper.tipoEstadoDTOtoTipoEstado(tEDTO));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarTipoEstado(
            @PathVariable Long id){

        return new ResponseEntity<>(tipoEstadoService.eliminarTipoEstado(id), HttpStatus.OK);
    }

}
