package org.example.videoclub.controllers;

import org.example.videoclub.services.EquipoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipo")
public class EquipoRestController {

    Logger log = LoggerFactory.getLogger(PeliculaRestController.class);

    @Autowired
    EquipoService equipoService;

    @GetMapping("/buscarEquipo")
    public ResponseEntity<List<String>> buscarEquipo(
            @RequestParam(defaultValue = "") String busqueda){

        List<String> lstEquipo = equipoService.busquedaEquipo(busqueda);

        return new ResponseEntity<>(lstEquipo, HttpStatus.OK);
    }



}
