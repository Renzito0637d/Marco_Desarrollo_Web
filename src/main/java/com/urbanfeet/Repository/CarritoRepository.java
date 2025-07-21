package com.urbanfeet.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.Usuario;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuario(Usuario usuario);
    Optional<Carrito> findByUsuarioId(Integer usuarioId);
}
