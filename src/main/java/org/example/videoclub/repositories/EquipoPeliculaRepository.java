package org.example.videoclub.repositories;

import org.example.videoclub.models.Equipo;
import org.example.videoclub.models.EquipoPelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoPeliculaRepository extends JpaRepository<EquipoPelicula, Long> {

    @Query("SELECT e" +
        " FROM EquipoPelicula ep" +
        " INNER JOIN ep.equipo e" +
        " INNER JOIN ep.seccion s" +
        " INNER JOIN ep.pelicula p" +
        " WHERE p.id = ?1" +
        " AND s.nombre = ?2")
    List<Equipo> findByIdPeliculaAndNombreSeccion(
            Long idPelicula,
            String nombreSeccion);

    @Query("SELECT e.nombre" +
            " FROM EquipoPelicula ep" +
            " INNER JOIN ep.equipo e" +
            " INNER JOIN ep.seccion s" +
            " INNER JOIN ep.pelicula p" +
            " WHERE p.id = ?1" +
            " AND s.nombre = ?2")
    List<String> findNombreByIdPeliculaAndNombreSeccion(
            Long idPelicula,
            String nombreSeccion);

    @Query("SELECT ep" +
        " FROM EquipoPelicula ep" +
        " INNER JOIN ep.pelicula p" +
        " WHERE p.id = ?1")
    List<EquipoPelicula> findByIdPelicula(
            Long idPelicula);

}
