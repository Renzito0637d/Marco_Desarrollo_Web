package com.urbanfeet.Service;

import java.util.List;

import com.urbanfeet.Entity.DireccionEnvio;
import com.urbanfeet.Entity.Pedido;
import com.urbanfeet.Entity.Usuario;

public interface PedidoService {
    public void savePedido(Pedido pedido);
    public Pedido obtenerPedido(Integer id);
    public List<Pedido> listarPedidosPorUsuario(Usuario usuario);
    public Pedido hacerPedido(Integer userId, DireccionEnvio direccionEnvio);
    public List<Pedido> ordenarPorFechaList(Integer usuarioId);
    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario);
}
