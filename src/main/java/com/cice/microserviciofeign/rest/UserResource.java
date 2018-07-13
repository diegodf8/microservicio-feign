package com.cice.microserviciofeign.rest;

import com.cice.microserviciofeign.feign.Saludo;
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

@Api(value = "UsuarioRest", description = "API de Gestion de usuarios")
@RestController("/usuario")
public class UserResource {


    @Autowired
    IGestionUsuario gestionUsuario;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Recupera id usuario by login y password", httpMethod = "GET", produces = "Long")
    public ResponseEntity<List<String>> getUsuario(@PathParam(value = "login") String login,
                                                   @PathParam(value = "password") String password) {
        gestionUsuario.getIdUsuario(login, password);
        return new ResponseEntity(gestionUsuario.getIdUsuario(login, password), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String crearUsuario(@PathParam(value = "nombre") String nombre,
                               @PathParam(value = "apellidos") String apellidos) {
        gestionUsuario.crearUsuario(nombre, apellidos);
        return "Usuario creado " + nombre;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String borrrarUsuario(@PathParam(value = "id") String id) {
        gestionUsuario.eliminarUsuario(id);
        return "Usuario borrado " + id;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String actualizarUsuario(@PathParam(value = "id") Long id) {
        gestionUsuario.actualizarUsuario(id);
        return "Usuario actualizado " + id;
    }


}
