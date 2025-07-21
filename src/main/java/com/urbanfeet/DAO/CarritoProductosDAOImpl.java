package com.urbanfeet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.CarritoProductos;
import com.urbanfeet.Repository.CarritoProductosRepository;

@Repository
public class CarritoProductosDAOImpl implements CarritoProductosDAO {

    @Autowired
    private CarritoProductosRepository carritoProductosRepository;
    // Implement the methods defined in CarritoProductosDAO interface
    @Override
    public List<CarritoProductos> listaCarritoProductos() {
        // Implementation here
        return carritoProductosRepository.findAll();
    }

    @Override
    public CarritoProductos listaCarritoProducto(Integer id) {
        // Implementation here
        return carritoProductosRepository.findById(id).get();
    }

    @Override
    public void guardarCarritoProducto(CarritoProductos carritoProducto) {
        carritoProductosRepository.save(carritoProducto);
    }

    @Override
    public void actualizarCarritoProducto(CarritoProductos carritoProducto) {
        carritoProductosRepository.save(carritoProducto);
    }

    @Override
    public void eliminarCarritoProducto(Integer id) {
        CarritoProductos carritoProducto = carritoProductosRepository.findById(id).orElse(null);
        if (carritoProducto != null) {
            carritoProductosRepository.delete(carritoProducto);
        }
    }

    @Override
    public List<CarritoProductos> listaCarritoProductosPorCarritoId(Integer carritoId) {
        // Implementation here
        return null;
    }

    @Override
    public List<CarritoProductos> listaCarritoProductosPorUsuarioId(Integer usuarioId) {
        // Implementation here
        return null;
    }

    @Override
    public List<CarritoProductos> listaCarritoProductosPorProductoId(Integer productoId) {
        // Implementation here
        return null;
    }

    @Override
    public void eliminarPorCarrito(Carrito carrito) {
        carritoProductosRepository.deleteAll(carrito.getProductos());
    }
    
}
