package org.example.videoclub.services;

import org.example.videoclub.models.Pais;
import org.example.videoclub.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepository;

    public Pais obtenerPais(Long id){

        Optional<Pais> p = paisRepository.findById(id);

        return p.isPresent() ? p.get() : null;

    }

    public List<Pais> obtenerPaises(){
        return paisRepository.findAll();
    }

    public Pais guardarPais(Pais p){
        return paisRepository.saveAndFlush(p);
    }

    public Boolean eliminarPais(Long id){

        Optional<Pais> p = paisRepository.findById(id);

        if(p.isPresent()){
            paisRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }


}
