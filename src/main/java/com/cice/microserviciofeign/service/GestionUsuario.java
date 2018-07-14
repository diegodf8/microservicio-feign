package com.cice.microserviciofeign.service;

import com.cice.microserviciofeign.entity.Usuario;
import com.cice.microserviciofeign.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("Gestion")
public class GestionUsuario implements IGestionUsuario{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Long getIdUsuario(String login, String password) {
        Long IdUsuario = 0L;
        Usuario usuario;
        usuario = usuarioRepository.findBylogin(login);
        IdUsuario = usuario.getId();
        return IdUsuario;
    }

    @Override
    public List<String> listaNombres() {
        return usuarioRepository.findAll().stream().
                map(usuario -> usuario.getLogin()).
                collect(Collectors.toList());

    }

    @Override
    public boolean crearUsuario(String login, String password) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setPassword(password);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public boolean actualizarUsuario(String id) {
        Usuario usuario = new Usuario();
        usuario = usuarioRepository.getOne(Long.parseLong(id));
        usuario.setPassword("passwordActu");
        System.out.println(usuario.getPassword());
        usuarioRepository.save(usuario);
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        usuarioRepository.deleteById(Long.parseLong(id));
        return true;
    }

    @Override
    public List<String> listaUsuarios() {
        return usuarioRepository.findAll().stream().
                map(usuario -> usuario.getLogin()).
                collect(Collectors.toList());

    }

}
