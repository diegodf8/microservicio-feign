package com.cice.microserviciofeign.rest;


import com.cice.microserviciofeign.feign.Productos;
import com.cice.microserviciofeign.service.IGestionUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
//Controller
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
        gestionUsuario.listaUsuarios().stream().forEach(x -> System.out.println("Usuario: " + x));
        System.out.println("Lista de productos " + productos.getProductos("1"));
        return new ResponseEntity<Long>(gestionUsuario.getIdUsuario(login, password), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String crearUsuario(@PathParam(value = "login") String login,
                               @PathParam(value = "password") String password) {
        gestionUsuario.crearUsuario(login, password);
        return "Usuario creado " + login;
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
