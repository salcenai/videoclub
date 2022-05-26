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

    @Query("SELECT p" +
        " FROM Pelicula p" +
        " INNER JOIN p.generoPeliculas gp" +
        " INNER JOIN gp.genero g" +
        " WHERE p.titulo LIKE %?2%" +
        " AND g.codigo = ?1")
    Page<Pelicula> findByCodigoGeneroAndTituloContaining(
            String codigoGenero,
            String busqueda,
            Pageable pagina);

    @Query("SELECT p" +
        " FROM Pelicula p" +
        " INNER JOIN p.estados e" +
        " INNER JOIN e.tipoEstado te" +
        " INNER JOIN e.usuario u" +
        " WHERE p.titulo LIKE %?2%" +
        " AND u.nombre = ?3" +
        " AND te.codigo = ?1" +
        " ORDER BY e.fecha DESC")
    Page<Pelicula> findByCodigoTipoEstadoAndTituloContainingAndNombreUsuarioOrderByFechaEstadoDesc(
            String codigoTipoEstado,
            String titulo,
            String nombreUsuario,
            Pageable pagina);


}
