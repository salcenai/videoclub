package org.example.videoclub.repositories;

import org.example.videoclub.models.Estado;
import org.example.videoclub.models.TipoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT e" +
        " FROM Estado e" +
        " INNER JOIN e.pelicula p" +
        " WHERE p.id = ?1" +
        " AND (e.critica IS NOT NULL OR e.critica != '')")
    List<Estado> findByIdPeliculaAndCriticaIsNotNull(
            Long idPelicula);

    @Query("SELECT e" +
        " FROM Estado e" +
        " INNER JOIN e.pelicula p" +
        " INNER JOIN e.usuario u" +
        " WHERE p.id = ?1" +
        " AND u.nombre = ?2")
    Optional<Estado> findByIdPeliculaAndNombreUsuario(Long idPelicula, String nombreUsuario);

    @Query("SELECT AVG(e.puntuacion)" +
        " FROM Estado e" +
        " INNER JOIN e.pelicula p" +
        " WHERE p.id = ?1")
    Optional<Integer> avgPuntuacionByIdPelicula(Long idPelicula);

    @Query("SELECT COUNT(e.puntuacion)" +
        " FROM Estado e" +
        " INNER JOIN e.pelicula p" +
        " WHERE p.id = ?1")
    Long countPuntuacionByIdPelicula(Long idPelicula);

}
