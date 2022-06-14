package org.example.videoclub.controllers;

import com.sun.istack.NotNull;
import org.example.videoclub.errors.PeliculaExistenteException;
import org.example.videoclub.errors.PeliculaNoEncontradaException;
import org.example.videoclub.models.Pelicula;
import org.example.videoclub.models.dto.*;
import org.example.videoclub.models.mapper.PeliculaMapper;
import org.example.videoclub.services.PeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pelicula")
public class PeliculaRestController {

    Logger log = LoggerFactory.getLogger(PeliculaRestController.class);

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    PeliculaMapper peliculaMapper;

    public static final String PAGINA_POR_DEFECTO = "0";
    public static final String ELEMENTOS_POR_PAGINA = "18";

    @GetMapping("{id}")
    public ModelAndView obtener(
            @PathVariable Long id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView mav = new ModelAndView();

        try {
            PeliculaCompletaDTO pCDTO = peliculaService.obtenerPeliculaCompleta(id, auth.getName());
            mav.addObject("pelicula", pCDTO);
            mav.setViewName("pelicula");
        } catch (PeliculaNoEncontradaException e) {
            mav.setViewName("recursoNoEncontrado");
        }

        return mav;
    }

    @GetMapping("/buscarPeliculas")
    public ResponseEntity<PaginaPeliculasMiniaturaDTO> buscarPeliculas(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = PAGINA_POR_DEFECTO) int pagina,
            @RequestParam(defaultValue = ELEMENTOS_POR_PAGINA) int elementosPorPagina){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Pageable paging = PageRequest.of(pagina, elementosPorPagina);

        PaginaPeliculasMiniaturaDTO paginaPeliculasMiniaturaDTO = peliculaService.busquedaMiniaturaPeliculas(
                busqueda,
                auth.isAuthenticated() ? auth.getName() : null,
                paging);

        return new ResponseEntity<>(paginaPeliculasMiniaturaDTO, HttpStatus.OK);
    }

    @GetMapping("/buscarPeliculasAvanzado")
    public ResponseEntity<Map<String, Object>> buscarPeliculasAvanzado(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "") String codigoPais,
            @RequestParam(defaultValue = "") String codigoGenero,
            @RequestParam(defaultValue = "0") int anioDesde,
            @RequestParam(defaultValue = "9999") int anioHasta,
            @RequestParam(defaultValue = PAGINA_POR_DEFECTO) int pagina,
            @RequestParam(defaultValue = ELEMENTOS_POR_PAGINA) int elementosPorPagina){

        // https://www.bezkoder.com/spring-data-sort-multiple-columns/

//        si esta vacio es nulo

        // TODO Unificar los dos metodos, si es que se puede

        log.info("busqueda: " + busqueda);
        log.info("codigoPais: " + codigoPais);
        log.info("codigoGenero: " + codigoGenero);
        log.info("anioDesde: " + anioDesde);
        log.info("anioHasta: " + anioHasta);
        log.info("pagina: " + pagina);

        return null;
    }

    @GetMapping("/buscarPeliculasPorTipoEstado")
    public PaginaPeliculasMiniaturaDTO buscarPeliculasPorTipoEstado(
            @RequestParam(name = "busqueda", defaultValue = "") String busqueda,
            @RequestParam(name = "codigoTipoEstado") String codigoTipoEstado,
            @RequestParam(name = "pagina", defaultValue = PAGINA_POR_DEFECTO) int pagina,
            @RequestParam(name = "elementosPorPagina", defaultValue = ELEMENTOS_POR_PAGINA) int elementosPorPagina){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Pageable paging = PageRequest.of(pagina, elementosPorPagina);

        PaginaPeliculasMiniaturaDTO paginaPeliculasMiniaturaDTO = peliculaService.busquedaMiniaturaPeliculasPorCodigoTipoEstado(
                codigoTipoEstado,
                busqueda,
                auth.getName(),
                paging);

        return paginaPeliculasMiniaturaDTO;
    }

    @PostMapping(value = "/nueva")
    public ResponseEntity<String> nueva(
            @RequestBody PeliculaNuevaDTO pelicula){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("/nueva " + auth.getName() + " " + auth.getAuthorities() + " " + pelicula.getTitulo());

        if(pelicula.getTitulo() == null || pelicula.getTitulo().isBlank())
            return new ResponseEntity<>("Debe introducir un título de película", HttpStatus.INTERNAL_SERVER_ERROR);
        if(pelicula.getTituloCompacto() == null || pelicula.getTituloCompacto().isBlank())
            return new ResponseEntity<>("Debe introducir un título compacto de película", HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            peliculaService.nuevaPelicula(pelicula);
        } catch (PeliculaExistenteException e) {
            return new ResponseEntity<>("El nombre compacto ya existe", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Pelicula añadida con éxito", HttpStatus.OK);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<Boolean> guardarPelicula(
            @RequestBody PeliculaDTO pDTO){

        peliculaService.guardarPelicula(peliculaMapper.peliculaDTOtoPelicula(pDTO));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarPelicula(
            @PathVariable Long id){

        return new ResponseEntity<>(peliculaService.eliminarPelicula(id), HttpStatus.OK);
    }

}
