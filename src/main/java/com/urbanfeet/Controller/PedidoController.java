package com.urbanfeet.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.CarritoProductos;
import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.DireccionEnvio;
import com.urbanfeet.Entity.Pedido;
import com.urbanfeet.Entity.PedidoDetalle;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Service.CarritoProductosService;
import com.urbanfeet.Service.CarritoService;
import com.urbanfeet.Service.PedidoService;
import com.urbanfeet.Service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/confirmar")
    public String confirmarPedido(@AuthenticationPrincipal Usuario usuario,
                                   Model model) {
        try {
            Direccion direccion = usuario.getDireccion();
            if (direccion == null) {
                model.addAttribute("error", "Debes registrar tu dirección antes de hacer un pedido.");
                return "carrito";
            }

            DireccionEnvio direccionEnvio = new DireccionEnvio(
                    direccion.getCalle(),
                    direccion.getDistrito(),
                    direccion.getProvincia(),
                    direccion.getDepartamento(),
                    direccion.getReferencia()
            );

            Pedido pedido = pedidoService.hacerPedido(usuario.getId(), direccionEnvio);
            model.addAttribute("pedido", pedido);
            return "pedido_exitoso";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "carrito";
        }
    }

    @GetMapping("/mis-pedidos")
public String verMisPedidos(@AuthenticationPrincipal Usuario usuario, Model model) {
    List<Pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(usuario);
    model.addAttribute("pedidos", pedidos);
    return "MisPedidos";
}

}
