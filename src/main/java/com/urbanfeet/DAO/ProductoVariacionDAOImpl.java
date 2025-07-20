package com.urbanfeet.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.ProductoVariacion;
import com.urbanfeet.Repository.ProductoVariacionRepository;

@Repository
public class ProductoVariacionDAOImpl implements ProductoVariacionDAO {

    // Autowired repository for database operations
    @Autowired
    private ProductoVariacionRepository productoVariacionRepository;

    @Override
    public List<ProductoVariacion> listaProductoVariaciones() {
        return productoVariacionRepository.findAll();
    }

    @Override
    public ProductoVariacion listaProductoVariacion(Integer id) {
        return productoVariacionRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarProductoVariacion(ProductoVariacion productoVariacion) {
        productoVariacionRepository.save(productoVariacion);
    }

    @Override
    public void actualizarProductoVariacion(ProductoVariacion productoVariacion) {
        productoVariacionRepository.save(productoVariacion);
    }

    @Override
    public void eliminarProductoVariacion(Integer id) {
        ProductoVariacion productoVariacion = productoVariacionRepository.findById(id).orElse(null);
        if (productoVariacion != null) {
            productoVariacionRepository.delete(productoVariacion);
        }
    }

    @Override
    public Optional<ProductoVariacion> buscarPorProductoYColor(Integer productoId, String color) {
        return productoVariacionRepository.findByProductoIdAndColor(productoId, color);
    }
    
}
