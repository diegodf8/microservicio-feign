package com.cice.microserviciofeign.repository;

import com.cice.microserviciofeign.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findBylogin(String login);
}
