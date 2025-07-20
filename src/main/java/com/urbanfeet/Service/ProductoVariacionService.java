package com.urbanfeet.Service;

import java.util.List;
import java.util.Optional;

import com.urbanfeet.Entity.ProductoVariacion;

public interface ProductoVariacionService {
    public List<ProductoVariacion> listaProductoVariaciones();
    public ProductoVariacion listaProductoVariacion(Integer id);
    public void guardarProductoVariacion(ProductoVariacion productoVariacion);
    public void actualizarProductoVariacion(ProductoVariacion productoVariacion);
    public void eliminarProductoVariacion(Integer id);
    public Optional<ProductoVariacion> buscarPorProductoYColor(Integer productoId, String color);
    
}