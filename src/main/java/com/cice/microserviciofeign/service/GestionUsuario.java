package com.cice.microserviciofeign.service;

import com.cice.microserviciofeign.entity.Usuario;
import com.cice.microserviciofeign.repository.UsuarioRepository;
import com.cice.microserviciofeign.rest.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
    public UsuarioDTO crearUsuario(String login, String password) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setPassword(password);
        Usuario result = usuarioRepository.save(usuario);
        return new UsuarioDTO(result.getId(), result.getLogin(), result.getPassword());
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

    @Override
    public  UsuarioDTO getUsuario(Long idUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        UsuarioDTO usuarioDTO = null;
        if(usuarioOptional.isPresent()){
            usuarioDTO = new UsuarioDTO(
                    usuarioOptional.get().getId(),
                    usuarioOptional.get().getLogin(),
                    usuarioOptional.get().getPassword()
            );
        }
        return usuarioDTO;
    }
}
