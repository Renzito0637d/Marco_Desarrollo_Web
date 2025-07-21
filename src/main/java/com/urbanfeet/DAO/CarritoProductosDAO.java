package com.urbanfeet.DAO;

import java.util.List;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.CarritoProductos;

public interface CarritoProductosDAO {
    public List<CarritoProductos> listaCarritoProductos();
    public CarritoProductos listaCarritoProducto(Integer id);
    public void guardarCarritoProducto(CarritoProductos carritoProducto);
    public void actualizarCarritoProducto(CarritoProductos carritoProducto);
    public void eliminarCarritoProducto(Integer id);
    public void eliminarPorCarrito(Carrito carrito);
    public List<CarritoProductos> listaCarritoProductosPorCarritoId(Integer carritoId);
    public List<CarritoProductos> listaCarritoProductosPorUsuarioId(Integer usuarioId);
    public List<CarritoProductos> listaCarritoProductosPorProductoId(Integer productoId);
}
