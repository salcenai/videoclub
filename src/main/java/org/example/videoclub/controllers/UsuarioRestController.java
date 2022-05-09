package org.example.videoclub.controllers;

import org.example.videoclub.errors.CorreoExistenteException;
import org.example.videoclub.errors.UsuarioExistenteException;
import org.example.videoclub.models.dto.UsuarioDTO;
import org.example.videoclub.models.dto.UsuarioNuevoDTO;
import org.example.videoclub.models.mapper.UsuarioMapper;
import org.example.videoclub.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    Logger log = LoggerFactory.getLogger(UsuarioRestController.class);

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioMapper usuarioMapper;

    @GetMapping(value = "/obtenerUsuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){

        return new ResponseEntity<>(usuarioMapper.usuariotoUsuarioDTO(usuarioService.obtenerUsuarios()), HttpStatus.OK);
    }

    @PostMapping(value = "/nuevo")
    public ResponseEntity<String> nuevo(
            @RequestBody UsuarioNuevoDTO usuario){

        if(usuario.getNombre() == null || usuario.getNombre().isBlank())
            return new ResponseEntity<>("Debe introducir un nombre de usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        if(usuario.getCorreo() == null || usuario.getCorreo().isBlank())
            return new ResponseEntity<>("Debe introducir una cuenta de correo", HttpStatus.INTERNAL_SERVER_ERROR);
        if(usuario.getContrasena() == null || usuario.getContrasena().isBlank())
            return new ResponseEntity<>("Debe introducir una contraseña", HttpStatus.INTERNAL_SERVER_ERROR);

        try{

            usuarioService.nuevoUsuario(usuario);

        } catch(UsuarioExistenteException e) {
            return new ResponseEntity<>("El nombre de usuario ya esta en uso", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(CorreoExistenteException e) {
            return new ResponseEntity<>("El correo ya esta en uso", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Usuario creado con éxito", HttpStatus.OK);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<Boolean> guardarUsuario(
            @RequestBody UsuarioDTO usuario){

        usuarioService.guardarUsuario(usuarioMapper.usuarioDTOtoUsuario(usuario));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarUsuario(
            @PathVariable Long id){

        return new ResponseEntity<>(usuarioService.eliminarUsuario(id), HttpStatus.OK);
    }

}
