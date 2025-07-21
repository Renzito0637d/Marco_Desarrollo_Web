package com.urbanfeet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Repository.UsuarioRepository;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

        @Override
    public List<Usuario> obtenerUsuariosPorRol(String rol) {
        return usuarioRepository.findByRol(Rol.valueOf(rol));
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        Usuario usuarioObj = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuarioObj);
    }

    @Override
    public Usuario autenticarUsuario(String email) {
        return usuarioRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void actualizarDatosPersonales(String emailActual, Usuario nuevosDatos) {
        Usuario usuario = usuarioRepository.findByEmail(emailActual)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(nuevosDatos.getNombre());
        usuario.setApellido(nuevosDatos.getApellido());
        usuario.setEmail(nuevosDatos.getEmail());
        usuario.setTelefono(nuevosDatos.getTelefono());

        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


}
