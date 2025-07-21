package com.urbanfeet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanfeet.DAO.CarritoDAO;
import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.Usuario;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoDAO carritoDAO;
    
    @Override
    public List<Carrito> listaCarritos() {
        return carritoDAO.listaCarritos();    
    }

    @Override
    public Carrito listaCarrito(Integer id) {        
        return carritoDAO.listaCarrito(id);
    }

    @Override
    public void guardarCarrito(Carrito carrito) {
        carritoDAO.guardarCarrito(carrito);
    }

    @Override
    public void actualizarCarrito(Carrito carrito) {
        carritoDAO.actualizarCarrito(carrito);
    }

    @Override
    public void eliminarCarrito(Integer id) {
        carritoDAO.eliminarCarrito(id);
    }

    @Override
    public Optional<Carrito> buscarPorUsuario(Usuario usuario) {
        return carritoDAO.buscarPorUsuario(usuario);
    }
    
}
