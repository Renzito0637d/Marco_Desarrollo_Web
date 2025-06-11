package com.urbanfeet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Repository.UsuarioRepository;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
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
}
