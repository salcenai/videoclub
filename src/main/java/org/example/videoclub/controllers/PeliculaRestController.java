package org.example.videoclub.controllers;

import org.example.videoclub.errors.PeliculaExistenteException;
import org.example.videoclub.errors.PeliculaNoEncontradaException;
import org.example.videoclub.models.Pelicula;
import org.example.videoclub.models.dto.PeliculaCompletaDTO;
import org.example.videoclub.models.dto.PeliculaDTO;
import org.example.videoclub.models.dto.PeliculaMiniaturaDTO;
import org.example.videoclub.models.dto.PeliculaNuevaDTO;
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

    private final String PAGINA_POR_DEFECTO = "0";
    private final String ELEMENTOS_POR_PAGINA = "12";

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

            return mav;
        }

        return mav;
    }

    @GetMapping("/buscarPeliculas")
    public ResponseEntity<Map<String, Object>> buscarPeliculas(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = PAGINA_POR_DEFECTO) int nPagina,
            @RequestParam(defaultValue = ELEMENTOS_POR_PAGINA) int size){

        try{

            // https://www.bezkoder.com/spring-boot-pagination-filter-jpa-pageable/

            Pageable paging = PageRequest.of(nPagina, size);

            //TODO Utilizar un DTO que tenga la lista de miniaturas mas la pagina actual, el total de paginas y el total de peliculas

            Page<Pelicula> pagePeliculas = peliculaService.busquedaPeliculas(busqueda, paging);
            List<PeliculaMiniaturaDTO> lstPeliculaMiniaturaDTO = peliculaMapper.peliculatopeliculaMiniaturaDTO(pagePeliculas.getContent());

            Map<String, Object> response = new HashMap<>();
            response.put("peliculas", lstPeliculaMiniaturaDTO);
            response.put("actualPagina", pagePeliculas.getNumber());
            response.put("totalPaginas", pagePeliculas.getTotalPages());
            response.put("totalPeliculas", pagePeliculas.getTotalElements());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/buscarPeliculasAvanzado")
    public ResponseEntity<Map<String, Object>> buscarPeliculasAvanzado(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "") String codigoPais,
            @RequestParam(defaultValue = "") String codigoGenero,
            @RequestParam(defaultValue = "0") int anioDesde,
            @RequestParam(defaultValue = "9999") int anioHasta,
            @RequestParam(defaultValue = PAGINA_POR_DEFECTO) int nPagina,
            @RequestParam(defaultValue = ELEMENTOS_POR_PAGINA) int size){

        // https://www.bezkoder.com/spring-data-sort-multiple-columns/

//        si esta vacio es nulo

        // TODO Unificar los dos metodos, si es que se puede

        log.info("busqueda: " + busqueda);
        log.info("codigoPais: " + codigoPais);
        log.info("codigoGenero: " + codigoGenero);
        log.info("anioDesde: " + anioDesde);
        log.info("anioHasta: " + anioHasta);
        log.info("nPagina: " + nPagina);


        return null;

    }

    @PostMapping(value = "/nueva")
    public ResponseEntity<String> nueva(
            @RequestBody PeliculaNuevaDTO pelicula){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("/nuevo " + auth.getName() + " " + auth.getAuthorities() + " " + pelicula.getTitulo());

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
