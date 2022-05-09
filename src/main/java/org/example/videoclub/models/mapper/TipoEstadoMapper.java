package org.example.videoclub.models.mapper;

import org.example.videoclub.models.TipoEstado;
import org.example.videoclub.models.dto.TipoEstadoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoEstadoMapper {

    public TipoEstado tipoEstadoDTOtoTipoEstado(
            TipoEstadoDTO tEDTO){

        if(tEDTO == null){ return null; }

        TipoEstado tE = new TipoEstado();

        tE.setId(tEDTO.getId());
        tE.setCodigo(tEDTO.getCodigo());
        tE.setNombre(tEDTO.getNombre());

        return tE;
    }

    public List<TipoEstado> tipoEstadoDTOtoTipoEstado(
            List<TipoEstadoDTO> lstTEPTO){

        if(lstTEPTO == null){ return null; }

        List<TipoEstado> lstP = new ArrayList<>();

        for(TipoEstadoDTO pDTO : lstTEPTO){
            lstP.add(tipoEstadoDTOtoTipoEstado(pDTO));
        }

        return lstP;
    }

    public TipoEstadoDTO tipoEstadotoTipoEstadoDTO(
            TipoEstado tE){

        if(tE == null){ return null; }

        TipoEstadoDTO tEDTO = new TipoEstadoDTO();

        tEDTO.setId(tE.getId());
        tEDTO.setCodigo(tE.getCodigo());
        tEDTO.setNombre(tE.getNombre());

        return tEDTO;
    }

    public List<TipoEstadoDTO> tipoEstadotoTipoEstadoDTO(
            List<TipoEstado> lstTE){

        if(lstTE == null){ return null; }

        List<TipoEstadoDTO> lstTEDTO = new ArrayList<>();

        for(TipoEstado p : lstTE){
            lstTEDTO.add(tipoEstadotoTipoEstadoDTO(p));
        }

        return lstTEDTO;
    }

}
