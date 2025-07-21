package com.urbanfeet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Pedido;
import com.urbanfeet.Entity.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar pedidos por usuario o estado
    List<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findByUsuarioEmail(String email);
    List<Pedido> findByUsuarioIdOrderByFechaPedidoDesc(Integer usuarioId);


}
