package com.urbanfeet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanfeet.DAO.ProductoVariacionDAO;
import com.urbanfeet.Entity.ProductoVariacion;

@Service
public class ProductoVariacionServiceImpl implements ProductoVariacionService {
    
    @Autowired
    private ProductoVariacionDAO productoVariacionDAO;

    @Override
    public List<ProductoVariacion> listaProductoVariaciones() {
        return productoVariacionDAO.listaProductoVariaciones();
    }
    @Override
    public ProductoVariacion listaProductoVariacion(Integer id) {
        return productoVariacionDAO.listaProductoVariacion(id);
    }
    @Override
    public void guardarProductoVariacion(ProductoVariacion productoVariacion) {
        productoVariacionDAO.guardarProductoVariacion(productoVariacion);
    }
    @Override
    public void actualizarProductoVariacion(ProductoVariacion productoVariacion) {
        productoVariacionDAO.actualizarProductoVariacion(productoVariacion);
    }
    @Override
    public void eliminarProductoVariacion(Integer id) {
        productoVariacionDAO.eliminarProductoVariacion(id);
    }
    @Override
    public Optional<ProductoVariacion> buscarPorProductoYColor(Integer productoId, String color) {
        return productoVariacionDAO.buscarPorProductoYColor(productoId, color);
    }

}
