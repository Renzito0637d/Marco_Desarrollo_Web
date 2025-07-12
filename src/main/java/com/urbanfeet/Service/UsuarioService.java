package com.urbanfeet.Service;

import java.util.List;

import com.urbanfeet.Entity.Model.*;
import com.urbanfeet.Entity.Usuario;

public interface UsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario obtenerUsuarioPorId(Integer id);
    
    public AuthResponse guardarUsuario(RegisterRequest request);
    public AuthResponse guardarUserAdmin(RegisterRequestAdmin request);
    public AuthResponse autenticarUsuario (AuthenticationRequest request);

    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Integer id);
}
