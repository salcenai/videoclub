package org.example.videoclub.controllers;

import org.example.videoclub.models.dto.GeneroDTO;
import org.example.videoclub.models.mapper.GeneroMapper;
import org.example.videoclub.services.GeneroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroRestController {

    Logger log = LoggerFactory.getLogger(GeneroRestController.class);

    @Autowired
    GeneroService generoService;

    @Autowired
    GeneroMapper generoMapper;

    @GetMapping(value = "/obtenerGeneros")
    public ResponseEntity<List<GeneroDTO>> obtenerGeneros(){

        return new ResponseEntity<>(generoMapper.generotoGeneroDTO(generoService.obtenerGeneros()), HttpStatus.OK);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<Boolean> guardarGenero(
            @RequestBody GeneroDTO gDTO){

        generoService.guardarGenero(generoMapper.generoDTOtoGenero(gDTO));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarGenero(
            @PathVariable Long id){

        return new ResponseEntity<>(generoService.eliminarGenero(id), HttpStatus.OK);
    }

}
