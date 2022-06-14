package org.example.videoclub.services;

import org.example.videoclub.errors.PeliculaExistenteException;
import org.example.videoclub.errors.PeliculaNoEncontradaException;
import org.example.videoclub.models.Estado;
import org.example.videoclub.models.Genero;
import org.example.videoclub.models.GeneroPelicula;
import org.example.videoclub.models.Pelicula;
import org.example.videoclub.models.dto.PaginaPeliculasMiniaturaDTO;
import org.example.videoclub.models.dto.PeliculaCompletaDTO;
import org.example.videoclub.models.dto.PeliculaNuevaDTO;
import org.example.videoclub.models.mapper.EstadoMapper;
import org.example.videoclub.models.mapper.PeliculaMapper;
import org.example.videoclub.repositories.EstadoRepository;
import org.example.videoclub.repositories.GeneroPeliculaRepository;
import org.example.videoclub.repositories.GeneroRepository;
import org.example.videoclub.repositories.PeliculaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    Logger log = LoggerFactory.getLogger(PeliculaService.class);

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    GeneroPeliculaRepository generoPeliculaRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PeliculaMapper peliculaMapper;

    @Autowired
    EstadoMapper estadoMapper;

    @Autowired
    StringService stringService;

    public PeliculaCompletaDTO obtenerPeliculaCompleta(Long idPelicula, String nombreUsuario) throws PeliculaNoEncontradaException {

        Optional<Pelicula> p = peliculaRepository.findById(idPelicula);

        if(!p.isPresent()){
            throw new PeliculaNoEncontradaException("La pelicula " + idPelicula + " no ha sido encontrada");
        }

        PeliculaCompletaDTO pCDTO = peliculaMapper.peliculaToPeliculaCompletaDTO(p.get());

        Optional<Estado> e = estadoRepository.findByIdPeliculaAndNombreUsuario(idPelicula, nombreUsuario);

        if(e.isPresent()){
            pCDTO.setCriticaPersonal(estadoMapper.estadoToCriticaDTO(e.get()));
        }

        return pCDTO;
    }

    public PaginaPeliculasMiniaturaDTO busquedaMiniaturaPeliculas(
            String busqueda,
            String nombreUsuario,
            Pageable pagina){

        Page<Pelicula> pagePeliculas = peliculaRepository.findByTituloContaining(busqueda, pagina);

        return peliculaMapper.pagePeliculasToPaginaPeliculasMiniaturaDTO(pagePeliculas, nombreUsuario);
    }

    public PaginaPeliculasMiniaturaDTO busquedaMiniaturaPeliculasPorGenero(
            String codigoGenero,
            String busqueda,
            String nombreUsuario,
            Pageable paging){

        Page<Pelicula> pagePeliculas = peliculaRepository.findByCodigoGeneroAndTituloContaining(
                codigoGenero,
                busqueda,
                paging);

        return peliculaMapper.pagePeliculasToPaginaPeliculasMiniaturaDTO(pagePeliculas, nombreUsuario);

    }

    public PaginaPeliculasMiniaturaDTO busquedaMiniaturaPeliculasPorCodigoTipoEstado(
            String codigoTipoEstado,
            String busqueda,
            String nombreUsuario,
            Pageable paging){

        Page<Pelicula> pagePeliculas = peliculaRepository.findByCodigoTipoEstadoAndTituloContainingAndNombreUsuarioOrderByFechaEstadoDesc(
                codigoTipoEstado,
                busqueda,
                nombreUsuario,
                paging);

        return peliculaMapper.pagePeliculasToPaginaPeliculasMiniaturaDTO(pagePeliculas, nombreUsuario);
    }

    public Pelicula guardarPelicula(Pelicula pelicula){
        return peliculaRepository.saveAndFlush(pelicula);
    }

    public Pelicula nuevaPelicula(PeliculaNuevaDTO pNDTO) throws PeliculaExistenteException {

        Optional<Pelicula> peliculaExistente = peliculaRepository.findByTituloCompacto(pNDTO.getTituloCompacto());

        if(peliculaExistente.isPresent()){
            throw new PeliculaExistenteException("La pelicula " + pNDTO.getTituloCompacto() + " ya existe");
        }

        List<Genero> lstGeneros = new ArrayList<>();
        for(String nombreGenero: pNDTO.getLstGeneros()){

            Optional<Genero> generoOpt = generoRepository.findByNombre(nombreGenero);

            Genero genero;
            if(generoOpt.isPresent()){
                genero = generoOpt.get();
            } else {
                Genero generoNuevo = new Genero();
                generoNuevo.setPrioridad(2);
                generoNuevo.setCodigo(stringService.toPascalCase(stringService.eliminarDiacriticos(nombreGenero)));
                generoNuevo.setNombre(nombreGenero);

                genero = generoRepository.saveAndFlush(generoNuevo);
            }

            lstGeneros.add(genero);
        }

        Pelicula pelicula = peliculaMapper.peliculaNuevaDTOtoPelicula(pNDTO);

        pelicula.setFechaAlta(new Date());

        Pelicula peliculaNueva = peliculaRepository.saveAndFlush(pelicula);

        for(int i = 0; i < lstGeneros.size(); i++){

            GeneroPelicula gp = new GeneroPelicula();

            gp.setOrden(i);
            gp.setPelicula(peliculaNueva);
            gp.setGenero(lstGeneros.get(i));

            generoPeliculaRepository.saveAndFlush(gp);
        }

        return peliculaNueva;
    }

    public Boolean eliminarPelicula(Long id){

        Optional<Pelicula> p = peliculaRepository.findById(id);

        if(p.isPresent()){
            peliculaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void actualizarPuntuacionMedia(Long idPelicula) throws PeliculaNoEncontradaException {

        Optional<Pelicula> pelicula = peliculaRepository.findById(idPelicula);
        if(pelicula.isPresent()){

        } else {
            throw new PeliculaNoEncontradaException("La pelicula " + idPelicula + " no ha sido encontrada");
        }

        Optional<Integer> puntuacionMedia = estadoRepository.avgPuntuacionByIdPelicula(idPelicula);
        if(puntuacionMedia.isPresent()){
            pelicula.get().setPuntuacionMedia(puntuacionMedia.get());
        } else {
            pelicula.get().setPuntuacionMedia(null);
        }

        Long numVotos = estadoRepository.countPuntuacionByIdPelicula(idPelicula);
        pelicula.get().setNumVotos(numVotos);

        peliculaRepository.saveAndFlush(pelicula.get());
    }

}
