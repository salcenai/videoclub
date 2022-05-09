package org.example.videoclub.controllers;

import org.example.videoclub.errors.EstadoNoEncontradoException;
import org.example.videoclub.errors.PeliculaNoEncontradaException;
import org.example.videoclub.errors.UsuarioNoEncontradoException;
import org.example.videoclub.models.dto.EstadoNuevoDTO;
import org.example.videoclub.services.EstadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado")
public class EstadoRestController {

    Logger log = LoggerFactory.getLogger(EstadoRestController.class);

    @Autowired
    EstadoService estadoService;

    @PutMapping("/nuevo")
    public ResponseEntity<String> nuevo(
            @RequestBody EstadoNuevoDTO estadoNuevoDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("/estado/nuevo - " + auth.getName() + "/" + auth.getAuthorities() + " - " + estadoNuevoDTO.toString());

        if(estadoNuevoDTO.getIdPelicula() == null)
            return new ResponseEntity<>("Debe introducir el id de la pelicula", HttpStatus.INTERNAL_SERVER_ERROR);
        if(estadoNuevoDTO.getCodigoTipoEstado() == null || estadoNuevoDTO.getCodigoTipoEstado().isBlank())
            return new ResponseEntity<>("Debe introducir un tipo de estado de la pelicula", HttpStatus.INTERNAL_SERVER_ERROR);

        if(estadoNuevoDTO.getCritica() != null && estadoNuevoDTO.getCritica().isBlank())
            estadoNuevoDTO.setCritica(null);

        try{
            estadoService.guardarEstado(estadoNuevoDTO, auth.getName());
        } catch (PeliculaNoEncontradaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UsuarioNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (EstadoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Estado añadido con éxito", HttpStatus.OK);

    }


}
