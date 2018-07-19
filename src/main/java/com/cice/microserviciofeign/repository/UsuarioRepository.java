package com.cice.microserviciofeign.repository;

import com.cice.microserviciofeign.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findBylogin(String login);
    //@Query("SELECT * FROM usuarios WHERE login = :login AND password = :password")
    Usuario findUsuarioByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
