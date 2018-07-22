package com.cice.microserviciofeign.rest;


import com.cice.microserviciofeign.entity.Usuario;
import com.cice.microserviciofeign.feign.Productos;
import com.cice.microserviciofeign.rest.dto.UsuarioDTO;
import com.cice.microserviciofeign.service.IGestionUsuario;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

//Controller
@Slf4j
@RestController("/")
public class UserResource {

    @Autowired
    IGestionUsuario gestionUsuario;

    @Autowired
    Productos productos;


    @RequestMapping(value = "todos",method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios(){
        List<UsuarioDTO> listausuarios = gestionUsuario.listaUsuarios();
        log.info("Lista de Usuarios: {}",listausuarios.toString());
        return ResponseEntity.ok(listausuarios);
    }

    @RequestMapping(value = "usuario",method = RequestMethod.GET)
    public ResponseEntity<Long> getUsuario(@PathParam(value = "login") String login,
                                           @PathParam(value = "password") String password) {

        return new ResponseEntity<Long>(gestionUsuario.getIdUsuario(login, password), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "usuario", method = RequestMethod.POST)
    public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {

        log.info("Usuario insertado: {}",usuarioDTO.toString());
        UsuarioDTO result = gestionUsuario.crearUsuario(usuarioDTO.getLogin(),usuarioDTO.getPassword());
        String location = String.format("http://localhost:8071/get-usuario/%s",result.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(location));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="usuario/{login}/{password}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> recuperarUsuarioRegistrado(@PathVariable String login,
                                                                 @PathVariable String password) {

       UsuarioDTO usuarioDTO = gestionUsuario.getUsuario(login, password);
       log.info("Usuario recuperado por login y password: {}",usuarioDTO.toString());
        return ResponseEntity.ok(usuarioDTO);
    }

    @RequestMapping(value = "usuario/{id}",method = RequestMethod.GET)
    public  ResponseEntity<UsuarioDTO> recuperarUsuarioPorId(@PathVariable(value = "id") Long id){

        log.info("Usuario recuperado por id:");
       return ResponseEntity.ok(gestionUsuario.getUsuario(id));
    }

    @RequestMapping(value = "usuario/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> elminarUsuarioYTodosSusProductos(@PathVariable Long id){
        log.info("Usuario eliminado por id y sus productos:");
       gestionUsuario.eliminarUsuario(id);
        return ResponseEntity.ok("Todo ha ido ok");
    }

    @RequestMapping(value = "usuario/{id}",method = RequestMethod.PUT)
    public String actualizarUsuario(@PathVariable Long id) {
        gestionUsuario.actualizarUsuario(id);
        return "Usuario actualizado con id:" + id;
    }

}
