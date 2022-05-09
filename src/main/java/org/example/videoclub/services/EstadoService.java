package org.example.videoclub.services;

import org.example.videoclub.errors.EstadoNoEncontradoException;
import org.example.videoclub.errors.PeliculaNoEncontradaException;
import org.example.videoclub.errors.UsuarioNoEncontradoException;
import org.example.videoclub.models.Estado;
import org.example.videoclub.models.Pelicula;
import org.example.videoclub.models.TipoEstado;
import org.example.videoclub.models.Usuario;
import org.example.videoclub.models.dto.EstadoNuevoDTO;
import org.example.videoclub.repositories.EstadoRepository;
import org.example.videoclub.repositories.PeliculaRepository;
import org.example.videoclub.repositories.TipoEstadoRepository;
import org.example.videoclub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    TipoEstadoRepository tipoEstadoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    public Estado guardarEstado(EstadoNuevoDTO estadoNuevoDTO, String nombreUsuario) throws PeliculaNoEncontradaException, UsuarioNoEncontradoException, EstadoNoEncontradoException {

        Optional<Estado> estadoExistente = estadoRepository.findByIdPeliculaAndNombreUsuario(estadoNuevoDTO.getIdPelicula(), nombreUsuario);

        Estado estadoNuevo = null;

        if(estadoExistente.isPresent()){
            estadoNuevo = estadoExistente.get();
        } else {
            estadoNuevo = new Estado();

            Optional<Usuario> usuario = usuarioRepository.findByNombre(nombreUsuario);
            if(usuario.isPresent()){
                estadoNuevo.setUsuario(usuario.get());
            } else {
                throw new UsuarioNoEncontradoException("El usuario " + nombreUsuario + " no ha sido encontrado");
            }

            Optional<Pelicula> pelicula = peliculaRepository.findById(estadoNuevoDTO.getIdPelicula());
            if(pelicula.isPresent()){
                estadoNuevo.setPelicula(pelicula.get());
            } else {
                throw new PeliculaNoEncontradaException("La pelicula " + estadoNuevoDTO.getIdPelicula() + " no ha sido encontrada");
            }

        }

        Optional<TipoEstado> tipoEstado = tipoEstadoRepository.findByCodigo(estadoNuevoDTO.getCodigoTipoEstado());
        if(tipoEstado.isPresent()){
            estadoNuevo.setTipoEstado(tipoEstado.get());
        } else {
            throw new EstadoNoEncontradoException("El estado de código " + estadoNuevoDTO.getCodigoTipoEstado() + " no ha sido encontrado");
        }

        estadoNuevo.setPuntuacion(estadoNuevoDTO.getPuntuacion());
        estadoNuevo.setCritica(estadoNuevoDTO.getCritica());
        estadoNuevo.setFecha(new Date());

        estadoNuevo = estadoRepository.saveAndFlush(estadoNuevo);

        peliculaService.actualizarPuntuacionMedia(estadoNuevoDTO.getIdPelicula());

        return estadoNuevo;
    }

}
