package org.example.videoclub.controllers;

import org.example.videoclub.errors.GeneroNoEncontradoException;
import org.example.videoclub.models.dto.GeneroDTO;
import org.example.videoclub.models.dto.PaginaPeliculasMiniaturaDTO;
import org.example.videoclub.models.mapper.GeneroMapper;
import org.example.videoclub.services.GeneroService;
import org.example.videoclub.services.PeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.example.videoclub.controllers.PeliculaRestController.ELEMENTOS_POR_PAGINA;
import static org.example.videoclub.controllers.PeliculaRestController.PAGINA_POR_DEFECTO;

@RestController
@RequestMapping("/genero")
public class GeneroRestController {

    Logger log = LoggerFactory.getLogger(GeneroRestController.class);

    @Autowired
    GeneroService generoService;

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    GeneroMapper generoMapper;

    @GetMapping(value = "/{codigoGenero}")
    public ModelAndView index(
            @PathVariable String codigoGenero){

        ModelAndView mav = new ModelAndView();

        try{
            mav.addObject("codigoGenero", codigoGenero);
            mav.addObject("genero", generoService.obtenerGeneroByCodigo(codigoGenero).getNombre());
            mav.setViewName("genero");
        } catch(GeneroNoEncontradoException e){
            mav.setViewName("recursoNoEncontrado");
        }

        return mav;
    }

    @GetMapping("/buscarGeneros")
    public ResponseEntity<List<String>> buscarGeneros(
            @RequestParam(defaultValue = "") String busqueda){

        List<String> lstGeneros = generoService.busquedaGeneros(busqueda);

        return new ResponseEntity<>(lstGeneros, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPeliculasPorGenero")
    public PaginaPeliculasMiniaturaDTO buscarPeliculasPorGenero(
            @RequestParam(name = "busqueda", defaultValue = "") String busqueda,
            @RequestParam(name = "codigoGenero", defaultValue = "") String codigoGenero,
            @RequestParam(name = "pagina", defaultValue = PAGINA_POR_DEFECTO) int pagina,
            @RequestParam(name = "elementosPorPagina", defaultValue = ELEMENTOS_POR_PAGINA) int elementosPorPagina){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Pageable paging = PageRequest.of(pagina, elementosPorPagina);

        PaginaPeliculasMiniaturaDTO paginaPeliculasMiniaturaDTO = peliculaService.busquedaMiniaturaPeliculasPorGenero(
                codigoGenero,
                busqueda,
                auth.isAuthenticated() ? auth.getName() : null,
                paging);

        return paginaPeliculasMiniaturaDTO;
    }

    @GetMapping(value = "/obtenerGeneros")
    public ResponseEntity<List<GeneroDTO>> obtenerGeneros(){

        return new ResponseEntity<>(generoMapper.generotoGeneroDTO(generoService.obtenerGeneros()), HttpStatus.OK);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<Boolean> guardarGenero(
            @RequestBody GeneroDTO gDTO){

        generoService.guardarGenero(generoMapper.generoDTOtoGenero(gDTO));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarGenero(
            @PathVariable Long id){

        return new ResponseEntity<>(generoService.eliminarGenero(id), HttpStatus.OK);
    }

}
