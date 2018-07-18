package com.cice.microserviciofeign.rest;


import com.cice.microserviciofeign.feign.Productos;
import com.cice.microserviciofeign.rest.dto.UsuarioDTO;
import com.cice.microserviciofeign.service.IGestionUsuario;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

//Controller
@Slf4j
@Api(value = "UsuarioRest", description = "API de Gestion de usuarios")
@RestController("get-usuario")
public class UserResource {

    @Autowired
    @Qualifier("Gestion")
    IGestionUsuario gestionUsuario;

    @Autowired
    Productos productos;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Long> getUsuario(@PathParam(value = "login") String login,
                                           @PathParam(value = "password") String password) {
        System.out.println(productos.getproductos("1"));
        gestionUsuario.listaUsuarios().stream().forEach(x -> System.out.println("Usuario: " + x));
        //System.out.println(productos.getHello());
        //System.out.println("Lista de productos " + productos.getProductos("1"));
        return new ResponseEntity<Long>(gestionUsuario.getIdUsuario(login, password), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        log.debug("Usuario recibido: ",usuarioDTO.toString());
        UsuarioDTO result = gestionUsuario.crearUsuario(usuarioDTO.getLogin(),usuarioDTO.getPassword());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String borrrarUsuario(@PathParam(value = "id") String id) {
        gestionUsuario.eliminarUsuario(id);
        return "Usuario borrado con id: " + id;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String actualizarUsuario(@PathParam(value = "id") String id) {
        gestionUsuario.actualizarUsuario(id);
        return "Usuario actualizado con id:" + id;
    }


}
