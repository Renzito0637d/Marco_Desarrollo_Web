package com.urbanfeet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Usuario;
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
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        System.out.println(">>> ID recibido: " + usuario.getId());
        System.out.println(">>> Dirección recibida: " + usuario.getDireccion());

        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuario.getId()));

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefono(usuario.getTelefono());
        usuarioExistente.setRol(usuario.getRol());

        // ✅ ACTUALIZA LA DIRECCIÓN CAMPO POR CAMPO
        if (usuario.getDireccion() != null) {
            if (usuarioExistente.getDireccion() == null) {
                usuarioExistente.setDireccion(new com.urbanfeet.Entity.Direccion());
            }

            var nueva = usuario.getDireccion();
            var existente = usuarioExistente.getDireccion();

            existente.setCalle(nueva.getCalle());
            existente.setDistrito(nueva.getDistrito());
            existente.setProvincia(nueva.getProvincia());
            existente.setDepartamento(nueva.getDepartamento());
            existente.setReferencia(nueva.getReferencia());
        }

        // ✅ SOLO ENCRIPTADA (ya lo hiciste en el service)
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuarioExistente.setPassword(usuario.getPassword());
        }

        usuarioRepository.save(usuarioExistente);
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
}
