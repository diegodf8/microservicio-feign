package com.cice.microserviciofeign.service;

import com.cice.microserviciofeign.rest.dto.UsuarioDTO;

import java.util.List;

public interface IGestionUsuario {

    /**
     * *Metodo que recupera el id del usuario
     *
     * @return
     */
    Long getIdUsuario(String login, String password);

    List<String> listaNombres();

    /**
     * Metodo que crea un usuario
     *
     * @param login
     * @return
     */
    UsuarioDTO crearUsuario(String login, String password);

    /**
     * Metodo para actualizar  un usuario en base de datos
     *
     * @param id
     * @return
     */
    boolean actualizarUsuario(String id);

    /**
     * Metodo para eliminar un usuario de base de datos
     *
     * @param id
     * @return
     */
    boolean eliminarUsuario(String id);

    List<String> listaUsuarios();
}

