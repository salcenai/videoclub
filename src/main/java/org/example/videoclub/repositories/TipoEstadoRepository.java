package org.example.videoclub.repositories;

import org.example.videoclub.models.TipoEstado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoEstadoRepository extends JpaRepository<TipoEstado, Long> {

    Optional<TipoEstado> findByCodigo(
            String codigo);

}
