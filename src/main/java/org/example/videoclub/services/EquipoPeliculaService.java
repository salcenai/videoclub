package org.example.videoclub.services;

import org.example.videoclub.models.EquipoPelicula;
import org.example.videoclub.repositories.EquipoPeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoPeliculaService {

    @Autowired
    EquipoPeliculaRepository equipoPeliculaRepository;

    public EquipoPelicula guardar(
            EquipoPelicula ep){

        return equipoPeliculaRepository.saveAndFlush(ep);
    }

    public void eliminarPorIdPelicula(
            Long idPelicula){

        List<EquipoPelicula> lstEquipoPelicula = equipoPeliculaRepository.findByIdPelicula(idPelicula);

        eliminar(lstEquipoPelicula);
    }

    public void eliminar(
            List<EquipoPelicula> lstEquipoPelicula){

        equipoPeliculaRepository.deleteAll(lstEquipoPelicula);
    }

}
