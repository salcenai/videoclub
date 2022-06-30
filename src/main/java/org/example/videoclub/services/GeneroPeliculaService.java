package org.example.videoclub.services;

import org.example.videoclub.models.EquipoPelicula;
import org.example.videoclub.models.Genero;
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

    public GeneroPelicula guardar(
            GeneroPelicula gp){

        return generoPeliculaRepository.saveAndFlush(gp);
    }

    public void eliminarPorIdPelicula(
            Long idPelicula){

        List<GeneroPelicula> lstGeneroPelicula = generoPeliculaRepository.findByIdPelicula(idPelicula);

        eliminar(lstGeneroPelicula);
    }

    public void eliminar(
            List<GeneroPelicula> lstGeneroPelicula){

        generoPeliculaRepository.deleteAll(lstGeneroPelicula);
    }

}
