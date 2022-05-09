package org.example.videoclub.controllers;

import org.example.videoclub.models.dto.PaisDTO;
import org.example.videoclub.models.mapper.PaisMapper;
import org.example.videoclub.services.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class PaisRestController {

    Logger log = LoggerFactory.getLogger(PaisRestController.class);

    @Autowired
    PaisService paisService;

    @Autowired
    PaisMapper paisMapper;

    @GetMapping(value = "/obtenerPaises")
    public ResponseEntity<List<PaisDTO>> obtenerPaises(){

        return new ResponseEntity<>(paisMapper.paistoPaisDTO(paisService.obtenerPaises()), HttpStatus.OK);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<Boolean> guardarPais(
            @RequestBody PaisDTO pDTO){

        paisService.guardarPais(paisMapper.paisDTOtoPais(pDTO));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarPais(
            @PathVariable Long id){

        return new ResponseEntity<>(paisService.eliminarPais(id), HttpStatus.OK);
    }


}
