package org.example.videoclub.models.mapper;

import org.example.videoclub.models.Usuario;
import org.example.videoclub.models.dto.UsuarioDTO;
import org.example.videoclub.models.dto.UsuarioNuevoDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Usuario usuarioDTOtoUsuario(
            UsuarioDTO uDTO){

        if(uDTO == null){ return null; }

        Usuario u = new Usuario();

        u.setId(uDTO.getId());
        u.setNombre(uDTO.getNombre());
        u.setCorreo(uDTO.getCorreo());
        u.setContrasena(uDTO.getContrasena());

        u.setActivo(uDTO.getActivo());
        if(uDTO.getFechaAlta() != null){
            try {
                u.setFechaAlta(sdf.parse(uDTO.getFechaAlta()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(uDTO.getFechaBaja() != null){
            try {
                u.setFechaBaja(sdf.parse(uDTO.getFechaBaja()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        u.setRol(uDTO.getRol());

        return u;
    }

    public List<Usuario> usuarioDTOtoUsuario(
            List<UsuarioDTO> lstUDTO){

        if(lstUDTO == null){ return null; }

        List<Usuario> lstU = new ArrayList<>();

        for(UsuarioDTO uDTO : lstUDTO){
            lstU.add(usuarioDTOtoUsuario(uDTO));
        }

        return lstU;
    }

    public UsuarioDTO usuariotoUsuarioDTO(
            Usuario u){

        if(u == null){ return null; }

        UsuarioDTO uDTO = new UsuarioDTO();

        uDTO.setId(u.getId());
        uDTO.setNombre(u.getNombre());
        uDTO.setCorreo(u.getCorreo());
        uDTO.setContrasena(u.getContrasena());
        uDTO.setActivo(u.getActivo());
        if(u.getFechaAlta() != null){
            uDTO.setFechaAlta(sdf.format(u.getFechaAlta()));
        }
        if(u.getFechaBaja() != null){
            uDTO.setFechaBaja(sdf.format(u.getFechaBaja()));
        }
        uDTO.setRol(u.getRol());

        return uDTO;
    }

    public List<UsuarioDTO> usuariotoUsuarioDTO(
            List<Usuario> lstU){

        if(lstU == null){ return null; }

        List<UsuarioDTO> lstUDTO = new ArrayList<>();

        for(Usuario u : lstU){
            lstUDTO.add(usuariotoUsuarioDTO(u));
        }

        return lstUDTO;
    }

    public Usuario usuarioNuevoDTOtoUsuario(
            UsuarioNuevoDTO uNDTO){

        if(uNDTO == null){ return null; }

        Usuario u = new Usuario();

        u.setNombre(uNDTO.getNombre());
        u.setCorreo(uNDTO.getCorreo());
        u.setContrasena(uNDTO.getContrasena());

        return u;
    }

}
