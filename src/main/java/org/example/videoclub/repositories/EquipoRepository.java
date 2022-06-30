package org.example.videoclub.repositories;

import org.example.videoclub.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    Optional<Equipo> findByNombre(String nombre);

    @Query("SELECT e.nombre" +
            " FROM Equipo e" +
            " WHERE e.nombre LIKE %?1%" +
            " ORDER BY e.nombre ASC")
    List<String> findNombreByNombreContainingOrderByNombreAsc(
            String busqueda);

}
