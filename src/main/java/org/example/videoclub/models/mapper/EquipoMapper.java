package org.example.videoclub.models.mapper;

import org.example.videoclub.models.Equipo;
import org.example.videoclub.models.dto.EquipoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipoMapper {

    public EquipoDTO equipoToEquipoDTO(Equipo e){

        if(e == null){ return null; }

        EquipoDTO eDTO = new EquipoDTO();

        eDTO.setId(e.getId());
        eDTO.setCodigo(e.getCodigo());
        eDTO.setNombre(e.getNombre());

        return eDTO;
    }

    public List<EquipoDTO> equipoToEquipoDTO(List<Equipo> lstEquipo){

        if(lstEquipo == null){ return null; }

        List<EquipoDTO> lstEquipoDTO = new ArrayList<>();
        for(Equipo e : lstEquipo){
            lstEquipoDTO.add(equipoToEquipoDTO(e));
        }

        return lstEquipoDTO;
    }

}
