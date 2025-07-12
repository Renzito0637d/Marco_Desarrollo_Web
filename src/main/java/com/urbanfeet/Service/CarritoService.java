package com.urbanfeet.Service;

import java.util.List;

import com.urbanfeet.Entity.Carrito;

public interface CarritoService {
public List<Carrito> listaCarritos();
    public Carrito listaCarrito(Integer id);
    public void guardarCarrito(Carrito carrito);
    public void actualizarCarrito(Carrito carrito);
    public void eliminarCarrito(Integer id);
}
