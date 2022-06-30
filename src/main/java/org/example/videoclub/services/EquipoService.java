package org.example.videoclub.services;

import org.example.videoclub.models.Equipo;
import org.example.videoclub.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    StringService stringService;

    public Optional<Equipo> obtenerPorNombre(
            String nombreGenero){

        return equipoRepository.findByNombre(nombreGenero);
    }

    public List<String> busquedaEquipo(
            String busqueda){

        return equipoRepository.findNombreByNombreContainingOrderByNombreAsc(busqueda);
    }


    public Equipo guardarNuevo(
            String nombreEquipo){

        Equipo equipoNuevo = new Equipo();
        equipoNuevo.setCodigo(stringService.toPascalCase(stringService.eliminarDiacriticos(nombreEquipo)));
        equipoNuevo.setNombre(nombreEquipo);

        return guardar(equipoNuevo);
    }

    private Equipo guardar(
            Equipo e){

        return equipoRepository.saveAndFlush(e);
    }

}
