package com.urbanfeet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanfeet.DAO.PedidoDAO;
import com.urbanfeet.Entity.DireccionEnvio;
import com.urbanfeet.Entity.Pedido;
import com.urbanfeet.Entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Override
    public void savePedido(Pedido pedido) {
        pedidoDAO.savePedido(pedido);
    }

    @Override
    public Pedido obtenerPedido(Integer id) {
        return pedidoDAO.obtenerPedido(id);
    }

    @Override
    public List<Pedido> listarPedidosPorUsuario(Usuario usuario) {
        return pedidoDAO.listarPedidosPorUsuario(usuario);
    }

    @Override
    public Pedido hacerPedido(Integer userId, DireccionEnvio direccionEnvio) {
        return pedidoDAO.hacerPedido(userId, direccionEnvio);
    }

    @Override
    public List<Pedido> ordenarPorFechaList(Integer usuarioId) {
        return pedidoDAO.ordenarPorFechaList(usuarioId);
    }

    @Override
    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario) {
        return pedidoDAO.obtenerPedidosPorUsuario(usuario);
    }

    
}
