package org.example.videoclub.services;

import org.example.videoclub.errors.PeliculaNoEncontradaException;
import org.example.videoclub.models.*;
import org.example.videoclub.models.assist.ESeccion;
import org.example.videoclub.models.dto.PaginaPeliculasMiniaturaDTO;
import org.example.videoclub.models.dto.PeliculaCompletaDTO;
import org.example.videoclub.models.dto.PeliculaDTO;
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

import java.util.*;

@Service
public class PeliculaService {

    Logger log = LoggerFactory.getLogger(PeliculaService.class);

    @Autowired
    GeneroService generoService;

    @Autowired
    EquipoService equipoService;

    @Autowired
    GeneroPeliculaService generoPeliculaService;

    @Autowired
    EquipoPeliculaService equipoPeliculaService;

    @Autowired
    SeccionService seccionService;

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

    public PeliculaDTO obtenerPeliculaDTO(
            Long idPelicula) throws PeliculaNoEncontradaException {


        Optional<Pelicula> p = peliculaRepository.findById(idPelicula);

        if(!p.isPresent()){
            throw new PeliculaNoEncontradaException("La pelicula " + idPelicula + " no ha sido encontrada");
        }

        return peliculaMapper.peliculaToPeliculaDTO(p.get());
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

    public Pelicula guardar(
            PeliculaDTO pDTO) {

        if(pDTO.getId() != null){
            generoPeliculaService.eliminarPorIdPelicula(pDTO.getId());
            equipoPeliculaService.eliminarPorIdPelicula(pDTO.getId());
        }

        List<Genero> lstGeneros = new ArrayList<>();
        for(String nombreGenero: pDTO.getLstGeneros()){
            Optional<Genero> generoOpt = generoService.obtenerPorNombre(nombreGenero);
            Genero genero;
            if(generoOpt.isPresent()){
                genero = generoOpt.get();
            } else {
                genero = generoService.guardarNuevo(nombreGenero);
            }
            lstGeneros.add(genero);
        }

        Map<ESeccion, List<String>> mapLstNombresEquipo = new HashMap<>();
        mapLstNombresEquipo.put(ESeccion.Direccion, pDTO.getLstDireccion());
        mapLstNombresEquipo.put(ESeccion.Guion, pDTO.getLstGuion());
        mapLstNombresEquipo.put(ESeccion.BandaSonora, pDTO.getLstBandaSonora());
        mapLstNombresEquipo.put(ESeccion.Fotografia, pDTO.getLstFotografia());
        mapLstNombresEquipo.put(ESeccion.Produccion, pDTO.getLstProduccion());
        mapLstNombresEquipo.put(ESeccion.Reparto, pDTO.getLstReparto());

        Map<ESeccion, List<Equipo>> mapLstEquipo = new HashMap<>();

        for (ESeccion eSeccion : mapLstNombresEquipo.keySet()) {
            List<Equipo> lstEquipo = new ArrayList<>();
            for(String nombreEquipo : mapLstNombresEquipo.get(eSeccion)){
                Optional<Equipo> equipoOpt = equipoService.obtenerPorNombre(nombreEquipo);
                Equipo equipo;
                if(equipoOpt.isPresent()){
                    equipo = equipoOpt.get();
                } else {
                    equipo = equipoService.guardarNuevo(nombreEquipo);
                }
                lstEquipo.add(equipo);
            }
            mapLstEquipo.put(eSeccion, lstEquipo);
        }

        Pelicula pelicula = peliculaMapper.peliculaDTOtoPelicula(pDTO);
        pelicula.setFechaAlta(new Date());

        pelicula = guardar(pelicula);

        for(int i = 0; i < lstGeneros.size(); i++){
            GeneroPelicula gp = new GeneroPelicula();

            gp.setOrden(i);
            gp.setPelicula(pelicula);
            gp.setGenero(lstGeneros.get(i));

            generoPeliculaService.guardar(gp);
        }

        for(ESeccion eSeccion : mapLstEquipo.keySet()){
            if(mapLstEquipo.get(eSeccion).size() > 0){
                Optional<Seccion> seccionOpt = seccionService.obtenerPorNombre(eSeccion.getNombre());

                for(Equipo equipo : mapLstEquipo.get(eSeccion)){

                    EquipoPelicula ep = new EquipoPelicula();

                    ep.setPelicula(pelicula);
                    ep.setSeccion(seccionOpt.get());
                    ep.setEquipo(equipo);
                    ep.setNotas(null);

                    equipoPeliculaService.guardar(ep);

                }
            }
        }

        return pelicula;
    }

    private Pelicula guardar(
            Pelicula p){

        return peliculaRepository.saveAndFlush(p);
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
