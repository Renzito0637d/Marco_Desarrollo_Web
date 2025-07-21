package com.urbanfeet.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.CarritoProductos;
import com.urbanfeet.Entity.DireccionEnvio;
import com.urbanfeet.Entity.Pedido;
import com.urbanfeet.Entity.PedidoDetalle;
import com.urbanfeet.Entity.ProductoVariacion;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Repository.CarritoProductosRepository;
import com.urbanfeet.Repository.CarritoRepository;
import com.urbanfeet.Repository.PedidoRepository;
import com.urbanfeet.Repository.ProductoVariacionRepository;
import com.urbanfeet.Repository.UsuarioRepository;

@Repository
public class PedidoDAOImpl implements PedidoDAO {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoVariacionRepository productoVariacionRepository;

    @Autowired
    private CarritoProductosRepository carritoProductosRepository;

    @Override
    public void savePedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public Pedido obtenerPedido(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> listarPedidosPorUsuario(Usuario usuario) {
        return pedidoRepository.findByUsuario(usuario);
    }

    @Override
    public Pedido hacerPedido(Integer userId, DireccionEnvio direccionEnvio) {
        // 1. Obtener el usuario
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Obtener el carrito del usuario
        Carrito carrito = carritoRepository.findByUsuarioId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        List<CarritoProductos> productosCarrito = carrito.getProductos();

        if (productosCarrito == null || productosCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // 3. Crear el nuevo pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado("Pendiente");
        pedido.setDireccionEnvio(direccionEnvio);

        List<PedidoDetalle> detalles = new ArrayList<>();

        for (CarritoProductos cp : productosCarrito) {
            ProductoVariacion variacion = cp.getProductoVariacion();

            if (variacion.getStock() < cp.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " +
                        variacion.getProducto().getNombre() +
                        " (Color: " + variacion.getColor() + ", Talla: " + variacion.getTalla() + ")");
            }

            // Descontar stock
            variacion.setStock(variacion.getStock() - cp.getCantidad());
            productoVariacionRepository.save(variacion);

            // Crear detalle del pedido
            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setPedido(pedido);
            detalle.setProducto(variacion);
            detalle.setCantidad(cp.getCantidad());
            detalle.setPrecioTotal(variacion.getPrecio() * cp.getCantidad());

            detalles.add(detalle);
        }

        pedido.setDetalles(detalles);

        // 4. Guardar el pedido (se guarda también PedidoDetalle por cascada)
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // 5. Limpiar carrito
        carritoProductosRepository.deleteAll(productosCarrito);

        return pedidoGuardado;
    }

    @Override
    public List<Pedido> ordenarPorFechaList(Integer usuarioId) {
        return pedidoRepository.findByUsuarioIdOrderByFechaPedidoDesc(usuarioId);
    }

    @Override
    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuarioId) {
        return pedidoRepository.findByUsuarioEmail(usuarioId.getEmail());
    }
    
}
