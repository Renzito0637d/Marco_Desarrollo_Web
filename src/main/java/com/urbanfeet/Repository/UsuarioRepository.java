package com.urbanfeet.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {    
    Optional<Usuario> findUserByEmail(String email);

    Usuario findByEmail(String email);

    List<Usuario> findByRol(Rol rol);

}
