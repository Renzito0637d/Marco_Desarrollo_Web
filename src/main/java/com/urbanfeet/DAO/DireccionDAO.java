package com.urbanfeet.DAO;

import java.util.List;

import com.urbanfeet.Entity.Direccion;

public interface DireccionDAO {
    public List<Direccion> listarUsuarios();
    public Direccion obtenerUsuarioPorId(Integer id);
    public void guardarUsuario(Direccion usuario);
    public void actualizarUsuario(Direccion usuario);
    public void eliminarUsuario(Integer id);
}
