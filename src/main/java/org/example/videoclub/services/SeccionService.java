package org.example.videoclub.services;

import org.example.videoclub.models.Seccion;
import org.example.videoclub.repositories.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeccionService {

    @Autowired
    SeccionRepository seccionRepository;

    public Optional<Seccion> obtenerPorNombre(
            String nombreSeccion){

        return seccionRepository.findByNombre(nombreSeccion);
    }


}
