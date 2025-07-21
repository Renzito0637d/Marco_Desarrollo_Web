package com.urbanfeet.Service;

import java.util.List;

import com.urbanfeet.Entity.Model.*;
import com.urbanfeet.Entity.Usuario;

public interface UsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario obtenerUsuarioPorId(Integer id);
    public List<Usuario> obtenerUsuariosPorRol(String rol);

    public AuthResponse guardarUsuario(RegisterRequest request);
    public AuthResponse guardarUserAdmin(RegisterRequestAdmin request);
    public AuthResponse autenticarUsuario (AuthenticationRequest request);

    public void actualizarDatosPersonales(String emailActual, Usuario nuevosDatos);
    public Usuario obtenerPorEmail(String email);
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Integer id);
}
