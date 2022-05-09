package org.example.videoclub.services;

import org.example.videoclub.errors.CorreoExistenteException;
import org.example.videoclub.errors.UsuarioExistenteException;
import org.example.videoclub.models.Usuario;
import org.example.videoclub.models.dto.UsuarioNuevoDTO;
import org.example.videoclub.models.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.videoclub.repositories.UsuarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    private final String ROL_POR_DEFECTO = "USER";
    private final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(4);

    public Usuario obtenerUsuario(Long id){

        Optional<Usuario> u = usuarioRepository.findById(id);

        return u.isPresent() ? u.get() : null;
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario nuevoUsuario(UsuarioNuevoDTO uNDTO) throws UsuarioExistenteException, CorreoExistenteException {

        Optional<Usuario> usuarioExistente = usuarioRepository.findByNombre(uNDTO.getNombre());

        if(usuarioExistente.isPresent()){
            throw new UsuarioExistenteException("El usuario " + uNDTO.getNombre() + " ya existe");
        }

        usuarioExistente = usuarioRepository.findByCorreo(uNDTO.getCorreo());

        if(usuarioExistente.isPresent()){
            throw new CorreoExistenteException("El correo " + uNDTO.getCorreo() + " ya esta en uso");
        }

        Usuario usuarioNuevo = usuarioMapper.usuarioNuevoDTOtoUsuario(uNDTO);
        usuarioNuevo.setContrasena(PASSWORD_ENCODER.encode(uNDTO.getContrasena()));
        usuarioNuevo.setActivo(true);
        usuarioNuevo.setFechaAlta(new Date());
        usuarioNuevo.setRol(ROL_POR_DEFECTO);

        return usuarioRepository.saveAndFlush(usuarioNuevo);
    }

    public Boolean eliminarUsuario(Long id){

        Optional<Usuario> u = usuarioRepository.findById(id);

        if(u.isPresent()){
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
