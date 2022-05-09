package org.example.videoclub.repositories;

import org.example.videoclub.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByCodigo(String codigo);

    @Query("SELECT g" +
        " FROM Genero g" +
        " INNER JOIN g.generosPelicula gp" +
        " INNER JOIN gp.pelicula p" +
        " WHERE p.id = ?1")
    List<Genero> findByIdPelicula(
            Long idPelicula);

}
