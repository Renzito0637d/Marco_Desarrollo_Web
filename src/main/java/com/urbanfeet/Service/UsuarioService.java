package com.urbanfeet.Service;

import java.util.List;

import com.urbanfeet.Entity.Usuario;

public interface UsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario obtenerUsuarioPorId(Integer id);
    public void guardarUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Integer id);
    public Usuario autenticarUsuario(String email, String password);
}
