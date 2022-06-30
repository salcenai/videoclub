package org.example.videoclub.repositories;

import org.example.videoclub.models.GeneroPelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GeneroPeliculaRepository extends JpaRepository<GeneroPelicula, Long> {

    @Query("SELECT gp" +
        " FROM GeneroPelicula gp" +
        " INNER JOIN gp.pelicula p" +
        " WHERE p.id = ?1")
    List<GeneroPelicula> findByIdPelicula(
            Long idPelicula);

}
