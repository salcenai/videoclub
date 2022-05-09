package org.example.videoclub.models.mapper;

import org.example.videoclub.models.Genero;
import org.example.videoclub.models.dto.GeneroDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public Genero generoDTOtoGenero(
            GeneroDTO gDTO){

        if(gDTO == null){ return null; }

        Genero g = new Genero();

        g.setId(gDTO.getId());
        g.setCodigo(gDTO.getCodigo());
        g.setNombre(gDTO.getNombre());

        return g;
    }

    public List<Genero> generoDTOtoGenero(
            List<GeneroDTO> lstGPTO){

        if(lstGPTO == null){ return null; }

        List<Genero> lstG = new ArrayList<>();

        for(GeneroDTO gDTO : lstGPTO){
            lstG.add(generoDTOtoGenero(gDTO));
        }

        return lstG;
    }

    public GeneroDTO generotoGeneroDTO(
            Genero g){

        if(g == null){ return null; }

        GeneroDTO gDTO = new GeneroDTO();

        gDTO.setId(g.getId());
        gDTO.setCodigo(g.getCodigo());
        gDTO.setNombre(g.getNombre());

        return gDTO;
    }

    public List<GeneroDTO> generotoGeneroDTO(
            List<Genero> lstG){

        if(lstG == null){ return null; }

        List<GeneroDTO> lstGDTO = new ArrayList<>();

        for(Genero p : lstG){
            lstGDTO.add(generotoGeneroDTO(p));
        }

        return lstGDTO;
    }

}
