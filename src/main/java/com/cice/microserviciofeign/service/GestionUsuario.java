package com.cice.microserviciofeign.service;

import com.cice.microserviciofeign.entity.Usuario;
import com.cice.microserviciofeign.feign.Productos;
import com.cice.microserviciofeign.repository.UsuarioRepository;
import com.cice.microserviciofeign.rest.dto.UsuarioDTO;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestionUsuario implements IGestionUsuario{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    Productos productoFeign;

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
    public boolean actualizarUsuario(Long id) {
        Usuario usuario = new Usuario();
        usuario = usuarioRepository.getOne(id);
        usuario.setPassword("passwordActu");
        System.out.println(usuario.getPassword());
        usuarioRepository.save(usuario);
        return false;
    }

    @Override
    public UsuarioDTO eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
        productoFeign.eliminarProductoByIdUsuario(id);
        System.out.println("Usuario eliminado por id y sus productos: " + id);
        return null;
    }

    @Override
    public List<UsuarioDTO> listaUsuarios() {

        List<UsuarioDTO> collect = usuarioRepository.findAll()
                .stream()
                .map(productoentity -> new UsuarioDTO(
                                productoentity.getId(),
                                productoentity.getLogin(),
                                productoentity.getPassword()
                        )
                ).collect(Collectors.toList());
        return collect ;

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

    @Override
    public UsuarioDTO getUsuario(String login, String password) {

        Usuario usuario = usuarioRepository.findUsuarioByLoginAndPassword(login, password);
        return new UsuarioDTO(usuario.getId(),usuario.getLogin(),usuario.getPassword());
    }
}
