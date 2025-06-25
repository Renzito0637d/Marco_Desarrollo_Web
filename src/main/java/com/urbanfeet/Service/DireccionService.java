package com.urbanfeet.Service;

import java.util.List;

import com.urbanfeet.Entity.Direccion;

public interface DireccionService {
    public List<Direccion> listaDireccions();
    public Direccion obtenerDirecionPorId(Integer id);
    public void guardarDireccion(Direccion direccion);
    public void actualizarDireccion(Direccion direccion);
    public void eliminarDireccion(Integer id);
}