package org.example.videoclub.services;

import org.example.videoclub.models.TipoEstado;
import org.example.videoclub.repositories.TipoEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEstadoService {

    @Autowired
    TipoEstadoRepository tipoEstadoRepository;

    public TipoEstado obtenerTipoEstado(Long id){

        Optional<TipoEstado> p = tipoEstadoRepository.findById(id);

        return p.isPresent() ? p.get() : null;

    }

    public List<TipoEstado> obtenerTiposEstado(){
        return tipoEstadoRepository.findAll();
    }

    public TipoEstado guardarTipoEstado(TipoEstado p){
        return tipoEstadoRepository.saveAndFlush(p);
    }

    public Boolean eliminarTipoEstado(Long id){

        Optional<TipoEstado> p = tipoEstadoRepository.findById(id);

        if(p.isPresent()){
            tipoEstadoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }


}
