package com.urbanfeet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urbanfeet.Config.JwtService;
import com.urbanfeet.DAO.UsuarioDAO;
import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Model.AuthResponse;
import com.urbanfeet.Entity.Model.AuthenticationRequest;
import com.urbanfeet.Entity.Model.RegisterRequest;
import com.urbanfeet.Entity.Model.RegisterRequestAdmin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceIml implements UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    @Override
    public List<Usuario> obtenerUsuariosPorRol(String rol) {
        return usuarioDAO.obtenerUsuariosPorRol(rol);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    @Override
    public AuthResponse guardarUsuario(RegisterRequest request) {
        var user = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.USER)
                .direccion(request.getDireccion())
                .build();
        usuarioDAO.guardarUsuario(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
    
    @Override
    public AuthResponse guardarUserAdmin(RegisterRequestAdmin request) {
        var user = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.ADMIN)
                .direccion(request.getDireccion())
                .build();
        usuarioDAO.guardarUsuario(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
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
    public AuthResponse autenticarUsuario(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = usuarioDAO.autenticarUsuario(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public void actualizarDatosPersonales(String emailActual, Usuario nuevosDatos) {
        usuarioDAO.actualizarDatosPersonales(emailActual, nuevosDatos);
    }

    @Override
    public Usuario obtenerPorEmail(String email) {
        return usuarioDAO.obtenerPorEmail(email);
    }

}
