package org.example.videoclub.repositories;

import org.example.videoclub.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    @Query("SELECT p" +
            " FROM Pais p" +
            " WHERE p.codigo = ?1")
    Pais findByCodigo(String codigo);

}
