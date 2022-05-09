package org.example.videoclub.models.mapper;

import org.example.videoclub.models.Pais;
import org.example.videoclub.models.dto.PaisDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaisMapper {

    public Pais paisDTOtoPais(
            PaisDTO pDTO){

        if(pDTO == null){ return null; }

        Pais p = new Pais();

        p.setId(pDTO.getId());
        p.setCodigo(pDTO.getCodigo());
        p.setNombre(pDTO.getNombre());

        return p;
    }

    public List<Pais> paisDTOtoPais(
            List<PaisDTO> lstPDTO){

        if(lstPDTO == null){ return null; }

        List<Pais> lstP = new ArrayList<>();

        for(PaisDTO pDTO : lstPDTO){
            lstP.add(paisDTOtoPais(pDTO));
        }

        return lstP;
    }

    public PaisDTO paistoPaisDTO(
            Pais p){

        if(p == null){ return null; }

        PaisDTO pDTO = new PaisDTO();

        pDTO.setId(p.getId());
        pDTO.setCodigo(p.getCodigo());
        pDTO.setNombre(p.getNombre());

        return pDTO;
    }

    public List<PaisDTO> paistoPaisDTO(
            List<Pais> lstP){

        if(lstP == null){ return null; }

        List<PaisDTO> lstUDTO = new ArrayList<>();

        for(Pais p : lstP){
            lstUDTO.add(paistoPaisDTO(p));
        }

        return lstUDTO;
    }

}
