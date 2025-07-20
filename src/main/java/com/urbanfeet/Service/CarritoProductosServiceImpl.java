package com.urbanfeet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanfeet.DAO.CarritoProductosDAO;
import com.urbanfeet.Entity.CarritoProductos;

@Service
public class CarritoProductosServiceImpl implements CarritoProductosService {

    @Autowired
    private CarritoProductosDAO carritoProductosDAO;

    @Override
    public List<CarritoProductos> listaCarritoProductos() {
        return carritoProductosDAO.listaCarritoProductos();
    }

    @Override
    public Optional<CarritoProductos> listaCarritoProducto(Integer id) {
        return Optional.ofNullable(carritoProductosDAO.listaCarritoProducto(id));
    }

    @Override
    public void guardarCarritoProducto(CarritoProductos carritoProducto) {
        carritoProductosDAO.guardarCarritoProducto(carritoProducto);
    }

    @Override
    public void actualizarCarritoProducto(CarritoProductos carritoProducto) {
        carritoProductosDAO.actualizarCarritoProducto(carritoProducto);
    }

    @Override
    public void eliminarCarritoProducto(Integer id) {
        carritoProductosDAO.eliminarCarritoProducto(id);
    }

    
}
