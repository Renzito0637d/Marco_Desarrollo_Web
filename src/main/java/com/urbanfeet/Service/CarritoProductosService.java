package com.urbanfeet.Service;

import java.util.List;
import java.util.Optional;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.CarritoProductos;

public interface CarritoProductosService {
    public List<CarritoProductos> listaCarritoProductos();
    public Optional<CarritoProductos> listaCarritoProducto(Integer id);
    public void guardarCarritoProducto(CarritoProductos carritoProducto);
    public void actualizarCarritoProducto(CarritoProductos carritoProducto);
    public void eliminarCarritoProducto(Integer id);
    public void eliminarPorCarrito(Carrito carrito);
}
