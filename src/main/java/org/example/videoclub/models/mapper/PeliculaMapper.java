package org.example.videoclub.models.mapper;

import org.example.videoclub.models.*;
import org.example.videoclub.models.assist.ESeccion;
import org.example.videoclub.models.dto.*;
import org.example.videoclub.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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
    EquipoPeliculaRepository equipoPeliculaRepository;

    @Autowired
    EstadoMapper estadoMapper;

    @Autowired
    GeneroMapper generoMapper;

    @Autowired
    EquipoMapper equipoMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Pelicula peliculaDTOtoPelicula(
            PeliculaDTO pDTO) {

        Pelicula p = new Pelicula();
        p.setId(pDTO.getId());
        p.setTitulo(pDTO.getTitulo());
        p.setTituloCompacto(pDTO.getTituloCompacto());
        if(pDTO.getCodigoPais() != null && !pDTO.getCodigoPais().isBlank()){
            Pais pais = paisRepository.findByCodigo(pDTO.getCodigoPais());
            p.setPais(pais);
        }
        p.setAnio(pDTO.getAnio());
        p.setDuracion(pDTO.getDuracion());
        p.setSinopsis(pDTO.getSinopsis());

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

        List<Genero> lstGeneros = generoRepository.findByIdPeliculaOrderByOrdenAsc(p.getId());
        pcDTO.setLstGeneros(generoMapper.generotoGeneroDTO(lstGeneros));

        List<Equipo> lstEquipoDireccion = equipoPeliculaRepository.findByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Direccion.getNombre());
        pcDTO.setLstDireccion(equipoMapper.equipoToEquipoDTO(lstEquipoDireccion));

        List<Equipo> lstEquipoGuion = equipoPeliculaRepository.findByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Guion.getNombre());
        pcDTO.setLstGuion(equipoMapper.equipoToEquipoDTO(lstEquipoGuion));

        List<Equipo> lstEquipoBandaSonora = equipoPeliculaRepository.findByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.BandaSonora.getNombre());
        pcDTO.setLstBandaSonora(equipoMapper.equipoToEquipoDTO(lstEquipoBandaSonora));

        List<Equipo> lstEquipoFotografia = equipoPeliculaRepository.findByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Fotografia.getNombre());
        pcDTO.setLstFotografia(equipoMapper.equipoToEquipoDTO(lstEquipoFotografia));

        List<Equipo> lstEquipoProduccion = equipoPeliculaRepository.findByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Produccion.getNombre());
        pcDTO.setLstProduccion(equipoMapper.equipoToEquipoDTO(lstEquipoProduccion));

        List<Equipo> lstEquipoReparto = equipoPeliculaRepository.findByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Reparto.getNombre());
        pcDTO.setLstReparto(equipoMapper.equipoToEquipoDTO(lstEquipoReparto));

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

    public PeliculaDTO peliculaToPeliculaDTO(
            Pelicula p){

        PeliculaDTO pDTO = new PeliculaDTO();

        pDTO.setId(p.getId());
        pDTO.setTitulo(p.getTitulo());
        pDTO.setTituloCompacto(p.getTituloCompacto());
        if(p.getPais() != null){
            Optional<Pais> pais = paisRepository.findById(p.getPais().getId());
            if(pais.isPresent()){
                pDTO.setCodigoPais(pais.get().getCodigo());
            }
        }
        pDTO.setAnio(p.getAnio());
        pDTO.setDuracion(p.getDuracion());
        pDTO.setSinopsis(p.getSinopsis());

        List<String> lstGeneros = generoRepository.findNombreGeneroByIdPelicula(p.getId());
        pDTO.setLstGeneros(lstGeneros);

        List<String> lstDireccion = equipoPeliculaRepository.findNombreByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Direccion.getNombre());
        pDTO.setLstDireccion(lstDireccion);
        List<String> lstGuion = equipoPeliculaRepository.findNombreByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Guion.getNombre());
        pDTO.setLstGuion(lstGuion);
        List<String> lstBandaSonora = equipoPeliculaRepository.findNombreByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.BandaSonora.getNombre());
        pDTO.setLstBandaSonora(lstBandaSonora);
        List<String> lstFotografia = equipoPeliculaRepository.findNombreByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Fotografia.getNombre());
        pDTO.setLstFotografia(lstFotografia);
        List<String> lstProduccion = equipoPeliculaRepository.findNombreByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Produccion.getNombre());
        pDTO.setLstProduccion(lstProduccion);
        List<String> lstReparto = equipoPeliculaRepository.findNombreByIdPeliculaAndNombreSeccion(p.getId(), ESeccion.Reparto.getNombre());
        pDTO.setLstReparto(lstReparto);

        return pDTO;
    }

}
