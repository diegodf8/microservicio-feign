package com.cice.microserviciofeign.service;

import com.cice.microserviciofeign.entity.Usuario;
import com.cice.microserviciofeign.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionUsuario {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Long getIdUsuario(String login, String password) {
        Long IdUsuario = 0L;
        Usuario usuario;
        usuario = usuarioRepository.findBylogin(login);
        IdUsuario = usuario.getId();
        return IdUsuario;
    }

    public boolean crearUsuario(String login, String password) {
        Usuario usuario = new Usuario();
        usuario.setNombre(login);
        usuarioRepository.save(usuario);
        return true;
    }

    public boolean actualizarUsuario(Long id) {
        Usuario usuario = new Usuario();
        usuario = usuarioRepository.getOne(id);
        usuarioRepository.save(usuario);
        return false;
    }

    public boolean eliminarUsuario(String id) {
        usuarioRepository.deleteById(Long.parseLong(id));
        return true;
    }


}
