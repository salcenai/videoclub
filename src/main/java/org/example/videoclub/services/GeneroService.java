package org.example.videoclub.services;

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

    public Genero obtenerGenero(Long id){

        Optional<Genero> p = generoRepository.findById(id);

        return p.isPresent() ? p.get() : null;

    }

    public List<Genero> obtenerGeneros(){
        return generoRepository.findAll();
    }

    public Genero guardarGenero(Genero p){
        return generoRepository.saveAndFlush(p);
    }

    public Boolean eliminarGenero(Long id){

        Optional<Genero> p = generoRepository.findById(id);

        if(p.isPresent()){
            generoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
