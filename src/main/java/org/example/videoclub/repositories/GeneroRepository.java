package org.example.videoclub.repositories;

import org.example.videoclub.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByCodigo(String codigo);

    Optional<Genero> findByNombre(String nombre);

    @Query("SELECT g" +
        " FROM Genero g" +
        " INNER JOIN g.generosPelicula gp" +
        " INNER JOIN gp.pelicula p" +
        " WHERE p.id = ?1" +
        " ORDER BY gp.orden ASC")
    List<Genero> findByIdPeliculaOrderByOrdenAsc(
            Long idPelicula);

    @Query("SELECT g.nombre" +
            " FROM Genero g" +
            " WHERE g.nombre LIKE %?1%" +
            " ORDER BY g.nombre ASC")
    List<String> findNombreByNombreContainingOrderByNombreAsc(
            String busqueda);

    @Query("SELECT g.nombre" +
            " FROM GeneroPelicula gp" +
            " INNER JOIN gp.pelicula p" +
            " INNER JOIN gp.genero g" +
            " WHERE p.id = ?1")
    List<String> findNombreGeneroByIdPelicula(
            Long idPelicula);


}
