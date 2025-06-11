package com.urbanfeet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanfeet.DAO.UsuarioDAO;
import com.urbanfeet.Entity.Usuario;

@Service
public class UsuarioServiceIml implements UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.guardarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioDAO.eliminarUsuario(id);
    }

    @Override
    public Usuario autenticarUsuario(String email, String password) {
        return usuarioDAO.autenticarUsuario(email, password);
    }
}
