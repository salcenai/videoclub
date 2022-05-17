package org.example.videoclub.models.mapper;

import org.example.videoclub.models.Estado;
import org.example.videoclub.models.Genero;
import org.example.videoclub.models.Pais;
import org.example.videoclub.models.Pelicula;
import org.example.videoclub.models.dto.*;
import org.example.videoclub.repositories.EstadoRepository;
import org.example.videoclub.repositories.GeneroRepository;
import org.example.videoclub.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PeliculaMapper {

    @Autowired
    PaisRepository paisRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    EstadoMapper estadoMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Pelicula peliculaDTOtoPelicula(
            PeliculaDTO pDTO){

        Pelicula p = new Pelicula();
        p.setId(pDTO.getId());
        Pais pais = paisRepository.findByCodigo(pDTO.getCodigoPais());
        p.setPais(pais);
        p.setTitulo(pDTO.getTitulo());
        p.setAnio(pDTO.getAnio());
        p.setDuracion(pDTO.getDuracion());

        return p;
    }

    public List<Pelicula> peliculaDTOtoPelicula(
            List<PeliculaDTO> lstPDTO){

        List<Pelicula> lstP = new ArrayList<>();
        for(PeliculaDTO pDTO : lstPDTO){
            lstP.add(peliculaDTOtoPelicula(pDTO));
        }

        return lstP;
    }

    public PeliculaDTO peliculatoPeliculaDTO(
            Pelicula pelicula){

        PeliculaDTO pDTO = new PeliculaDTO();
        pDTO.setId(pelicula.getId());
        pDTO.setCodigoPais(pelicula.getPais().getCodigo());
        pDTO.setTitulo(pelicula.getTitulo());
        pDTO.setAnio(pelicula.getAnio());
        pDTO.setDuracion(pelicula.getDuracion());

        return pDTO;
    }

    public List<PeliculaDTO> peliculatoPeliculaDTO(
            List<Pelicula> lstP){

        List<PeliculaDTO> lstPDTO = new ArrayList<>();
        for(Pelicula p : lstP){
            lstPDTO.add(peliculatoPeliculaDTO(p));
        }

        return lstPDTO;
    }

    public Pelicula peliculaNuevaDTOtoPelicula(
            PeliculaNuevaDTO pNDTO) {

        Pelicula p = new Pelicula();
        p.setTitulo(pNDTO.getTitulo());
        p.setTituloCompacto(pNDTO.getTituloCompacto());
        if(pNDTO.getCodigoPais() != null && !pNDTO.getCodigoPais().isBlank()){
            Pais pais = paisRepository.findByCodigo(pNDTO.getCodigoPais());
            p.setPais(pais);
        }
        p.setAnio(pNDTO.getAnio());
        p.setDuracion(pNDTO.getDuracion());
        p.setSinopsis(pNDTO.getSinopsis());

        return p;
    }

    public PeliculaMiniaturaDTO peliculatopeliculaMiniaturaDTO(
            Pelicula p){

        PeliculaMiniaturaDTO pMDTO = new PeliculaMiniaturaDTO();
        pMDTO.setId(p.getId());
        pMDTO.setTituloCompacto(p.getTituloCompacto());
        pMDTO.setTitulo(p.getTitulo());
        pMDTO.setAnio(p.getAnio());
        pMDTO.setPuntuacionMedia(p.getPuntuacionMedia());

        return pMDTO;
    }

    public PeliculaMiniaturaDTO peliculaToPeliculaMiniaturaDTO(
            Pelicula p,
            String nombreUsuario){

        PeliculaMiniaturaDTO peliculaMiniaturaDTO = new PeliculaMiniaturaDTO();
        peliculaMiniaturaDTO.setId(p.getId());
        peliculaMiniaturaDTO.setTituloCompacto(p.getTituloCompacto());
        peliculaMiniaturaDTO.setTitulo(p.getTitulo());
        peliculaMiniaturaDTO.setAnio(p.getAnio());
        peliculaMiniaturaDTO.setPuntuacionMedia(p.getPuntuacionMedia());

        if(nombreUsuario != null){
            Optional<Estado> estado = estadoRepository.findByIdPeliculaAndNombreUsuario(p.getId(), nombreUsuario);
            if(estado.isPresent()){
                peliculaMiniaturaDTO.setPuntuacionPersonal(estado.get().getPuntuacion());
                peliculaMiniaturaDTO.setFecha(sdf.format(estado.get().getFecha()));
            }
        }

        return peliculaMiniaturaDTO;
    }

    public List<PeliculaMiniaturaDTO> peliculatopeliculaMiniaturaDTO(
            List<Pelicula> lstP){

        List<PeliculaMiniaturaDTO> lstPMDTO = new ArrayList<>();
        for(Pelicula p : lstP){
            lstPMDTO.add(peliculatopeliculaMiniaturaDTO(p));
        }

        return lstPMDTO;
    }

    public PeliculaCompletaDTO peliculaToPeliculaCompletaDTO(
            Pelicula p) {

        PeliculaCompletaDTO pcDTO = new PeliculaCompletaDTO();

        pcDTO.setId(p.getId());
        pcDTO.setTituloCompacto(p.getTituloCompacto());
        pcDTO.setTitulo(p.getTitulo());
        if(p.getPais() != null){
            Optional<Pais> pais = paisRepository.findById(p.getPais().getId());
            if(pais.isPresent()){
                pcDTO.setCodigoPais(pais.get().getCodigo());
                pcDTO.setPais(pais.get().getNombre());
            }
        }
        pcDTO.setAnio(p.getAnio());
        pcDTO.setDuracion(p.getDuracion());
        pcDTO.setSinopsis(p.getSinopsis());
        pcDTO.setPuntuacionMedia(p.getPuntuacionMedia());
        pcDTO.setNumVotos(p.getNumVotos());

        List<Genero> lstGeneros = generoRepository.findByIdPelicula(p.getId());

        Map<String, String> mapGeneros = new HashMap<>();
        Map<String, String> mapSubgeneros = new HashMap<>();

        for(Genero g: lstGeneros){
            if(g.getPrioridad() == 1){
                mapGeneros.put(g.getCodigo(), g.getNombre());
            } else {
                mapSubgeneros.put(g.getCodigo(), g.getNombre());
            }
        }
        pcDTO.setMapGeneros(mapGeneros);
        pcDTO.setMapSubgeneros(mapSubgeneros);

        List<Estado> lstEstado = estadoRepository.findByIdPeliculaAndCriticaIsNotNull(p.getId());

        pcDTO.setLstCriticas(estadoMapper.estadoToCriticaDTO(lstEstado));

        return pcDTO;
    }

    public PaginaPeliculasMiniaturaDTO pagePeliculasToPaginaPeliculasMiniaturaDTO(
            Page<Pelicula> pagePeliculas,
            String nombreUsuario) {

        List<PeliculaMiniaturaDTO> lstPeliculaMiniaturaDTO = new ArrayList<>();

        for(Pelicula p: pagePeliculas.getContent()){
            lstPeliculaMiniaturaDTO.add(peliculaToPeliculaMiniaturaDTO(p, nombreUsuario));
        }

        PaginaPeliculasMiniaturaDTO paginaPeliculasMiniaturaDTO = new PaginaPeliculasMiniaturaDTO();

        paginaPeliculasMiniaturaDTO.setPeliculas(lstPeliculaMiniaturaDTO);
        paginaPeliculasMiniaturaDTO.setPaginaActual(pagePeliculas.getNumber());
        paginaPeliculasMiniaturaDTO.setTotalPaginas(pagePeliculas.getTotalPages());
        paginaPeliculasMiniaturaDTO.setTotalPeliculas(pagePeliculas.getTotalElements());

        return paginaPeliculasMiniaturaDTO;
    }

}
