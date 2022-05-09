package org.example.videoclub.services;

import org.example.videoclub.models.GeneroPelicula;
import org.example.videoclub.repositories.GeneroPeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroPeliculaService {

    @Autowired
    GeneroPeliculaRepository generoPeliculaRepository;

    public GeneroPelicula obtenerGeneroPelicula(Long id){

        Optional<GeneroPelicula> p = generoPeliculaRepository.findById(id);

        return p.isPresent() ? p.get() : null;

    }

    public List<GeneroPelicula> obtenerGeneroPeliculaes(){
        return generoPeliculaRepository.findAll();
    }

    public GeneroPelicula guardarGeneroPelicula(GeneroPelicula p){
        return generoPeliculaRepository.saveAndFlush(p);
    }

    public Boolean eliminarGeneroPelicula(Long id){

        Optional<GeneroPelicula> p = generoPeliculaRepository.findById(id);

        if(p.isPresent()){
            generoPeliculaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
