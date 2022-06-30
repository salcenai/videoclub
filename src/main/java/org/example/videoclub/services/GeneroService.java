package org.example.videoclub.services;

import org.example.videoclub.errors.GeneroNoEncontradoException;
import org.example.videoclub.models.Genero;
import org.example.videoclub.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    StringService stringService;


    public Genero obtenerGeneroPorCodigo(
            String codigoGenero) throws GeneroNoEncontradoException {

        Optional<Genero> genero = generoRepository.findByCodigo(codigoGenero);

        if(!genero.isPresent()){
            throw new GeneroNoEncontradoException("No se ha encontrado el género con código: " + codigoGenero);
        }

        return genero.get();
    }

    public Optional<Genero> obtener(
            Long id){

        return generoRepository.findById(id);
    }

    public Optional<Genero> obtenerPorNombre(
            String nombreGenero){

        return generoRepository.findByNombre(nombreGenero);
    }

    public List<Genero> obtener(
            ){
        return generoRepository.findAll();
    }

    public List<String> busqueda(
            String busqueda){

        return generoRepository.findNombreByNombreContainingOrderByNombreAsc(busqueda);
    }

    public Genero guardarNuevo(
            String nombreGenero){

        Genero generoNuevo = new Genero();
        generoNuevo.setPrioridad(2);
        generoNuevo.setCodigo(stringService.toPascalCase(stringService.eliminarDiacriticos(nombreGenero)));
        generoNuevo.setNombre(nombreGenero);

        return guardar(generoNuevo);
    }

    private Genero guardar(
            Genero g){

        return generoRepository.saveAndFlush(g);
    }

    public Boolean eliminarGenero(
            Long id){

        Optional<Genero> p = generoRepository.findById(id);

        if(p.isPresent()){
            generoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
