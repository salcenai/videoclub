package org.example.videoclub.repositories;

import org.example.videoclub.models.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Optional<Pelicula> findByTituloCompacto(String tituloCompacto);

    @Query("SELECT p" +
        " FROM Pelicula p" +
        " WHERE p.titulo LIKE %?1%")
    Page<Pelicula> findByTituloContaining(String titulo, Pageable pagina);

}
