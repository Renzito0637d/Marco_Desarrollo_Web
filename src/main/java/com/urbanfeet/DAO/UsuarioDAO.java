package com.urbanfeet.DAO;

import java.util.List;

import com.urbanfeet.Entity.Usuario;

public interface UsuarioDAO {
    public List<Usuario> listarUsuarios();
    public Usuario obtenerUsuarioPorId(Integer id);
    public void guardarUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Integer id);
    public Usuario autenticarUsuario(String email);
    public Usuario buscarPorEmail(String email);
}
