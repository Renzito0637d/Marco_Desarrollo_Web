package com.urbanfeet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Repository.CarritoRepository;

@Repository
public class CarritoDAOImpl implements CarritoDAO {

    @Autowired
    private CarritoRepository carritoRepository;
    // Implement the methods from CarritoDAO interface
    @Override
    public List<Carrito> listaCarritos() {
        // Implementation code here
        return carritoRepository.findAll();
    }

    @Override
    public Carrito listaCarrito(Integer id) {
        // Implementation code here
        return carritoRepository.findById(id).get();
    }

    @Override
    public void guardarCarrito(Carrito carrito) {
        carritoRepository.save(carrito);
    }

    @Override
    public void actualizarCarrito(Carrito carrito) {
        carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(Integer id) {
        Carrito carritoObj = carritoRepository.findById(id).get();
        carritoRepository.delete(carritoObj);
    }
    
}
