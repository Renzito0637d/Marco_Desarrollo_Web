package com.urbanfeet.DAO;

import java.util.List;
import java.util.Optional;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.Usuario;

public interface CarritoDAO {
    public List<Carrito> listaCarritos();
    public Carrito listaCarrito(Integer id);
    public void guardarCarrito(Carrito carrito);
    public void actualizarCarrito(Carrito carrito);
    public void eliminarCarrito(Integer id);
    public Optional<Carrito> buscarPorUsuario(Usuario usuario);
}
