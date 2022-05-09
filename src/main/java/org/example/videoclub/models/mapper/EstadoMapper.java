package org.example.videoclub.models.mapper;

import org.example.videoclub.models.Estado;
import org.example.videoclub.models.TipoEstado;
import org.example.videoclub.models.Usuario;
import org.example.videoclub.models.dto.CriticaDTO;
import org.example.videoclub.repositories.TipoEstadoRepository;
import org.example.videoclub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class EstadoMapper {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TipoEstadoRepository tipoEstadoRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public CriticaDTO estadoToCriticaDTO(
            Estado e){

        CriticaDTO c = new CriticaDTO();

        c.setId(e.getId());
        Optional<Usuario> u = usuarioRepository.findById(e.getUsuario().getId());
        if(u.isPresent()){
            c.setNombreUsuario(u.get().getNombre());
        }
        c.setPuntuacion(e.getPuntuacion());
        Optional<TipoEstado> tipoEstado = tipoEstadoRepository.findById(e.getTipoEstado().getId());
        if(tipoEstado.isPresent()){
            c.setCodigoTipoEstado(tipoEstado.get().getCodigo());
        }
        if(e.getFecha() != null){
            c.setFecha(sdf.format(e.getFecha()));
        }
        c.setCritica(e.getCritica());

        return c;
    }

    public List<CriticaDTO> estadoToCriticaDTO(
            List<Estado> lstEstados){

        List<CriticaDTO> lstCriticas = new ArrayList<CriticaDTO>();
        for(Estado e: lstEstados){
            lstCriticas.add(estadoToCriticaDTO(e));
        }

        return lstCriticas;
    }



}
