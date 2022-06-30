package org.example.videoclub.repositories;

import org.example.videoclub.models.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {

    public Optional<Seccion> findByNombre(String nombreSeccion);

}
