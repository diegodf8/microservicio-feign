package com.cice.microserviciofeign.service;

import java.util.List;

public interface IGestionUsuario {

    /**
     * *Metodo que recupera el id del usuario
     *
     * @return
     */
    Long getIdUsuario(String login, String password);


    /**
     * Metodo que crea un usuario
     *
     * @param login
     * @return
     */
    boolean crearUsuario(String login, String password);


    /**
     * Metodo para actualizar  un usuario en base de datos
     *
     * @param id
     * @return
     */
    boolean actualizarUsuario(Long id);

    /**
     * Metodo para eliminar un usuario de base de datos
     *
     * @param login
     * @return
     */
    boolean eliminarUsuario(String login);
}

