package com.urbanfeet.Controller;


import com.urbanfeet.Entity.Carrito;
import com.urbanfeet.Entity.CarritoProductos;
import com.urbanfeet.Entity.ProductoVariacion;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Service.CarritoProductosService;
import com.urbanfeet.Service.CarritoService;
import com.urbanfeet.Service.ProductoVariacionService;
import com.urbanfeet.Service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final UsuarioService usuarioService;
    private final ProductoVariacionService productoVariacionService;
    private final CarritoService carritoService;
    private final CarritoProductosService carritoProductosService;

    @PostMapping("/agregar")
    public String agregarAlCarrito(
            @RequestParam("productoId") Integer productoId,
            @RequestParam("colorSeleccionado") String color,
            @RequestParam("talla") String talla,
            @RequestParam("precioSeleccionado") Double precio,
            Principal principal) {

        Usuario usuario = usuarioService.buscarPorEmail(principal.getName());

        Carrito carrito = carritoService.buscarPorUsuario(usuario)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);
                    carritoService.guardarCarrito(nuevoCarrito);
                    return nuevoCarrito;
                });

        Optional<ProductoVariacion> variacionOpt = productoVariacionService
                .buscarPorProductoYColor(productoId, color);

        if (variacionOpt.isEmpty()) {
            return "redirect:/error";
        }

        ProductoVariacion variacion = variacionOpt.get();

        Optional<CarritoProductos> yaExiste = carrito.getProductos().stream()
                .filter(cp -> cp.getProductoVariacion().getId().equals(variacion.getId()))
                .findFirst();

        if (yaExiste.isPresent()) {

            CarritoProductos cp = yaExiste.get();
            cp.setCantidad(cp.getCantidad() + 1);
            carritoProductosService.guardarCarritoProducto(cp);
        } else {

            CarritoProductos cp = new CarritoProductos();
            cp.setCarrito(carrito);
            cp.setProductoVariacion(variacion);
            cp.setCantidad(1);
            carritoProductosService.guardarCarritoProducto(cp);
        }

        return "redirect:/carrito/ver";
    }

    @GetMapping("/ver")
    public String verCarrito(Model model, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            model.addAttribute("mensajeError", "No se encontró un usuario con el correo: " + email);
            return "error";
        }

        Optional<Carrito> carritoOpt = carritoService.buscarPorUsuario(usuario);

        if (carritoOpt.isPresent()) {
            Carrito carrito = carritoOpt.get();
            model.addAttribute("carrito", carrito);

            double subtotal = carrito.getProductos().stream()
                    .mapToDouble(item -> item.getCantidad() * item.getProductoVariacion().getPrecio())
                    .sum();
            model.addAttribute("subtotal", subtotal);

        } else {
            model.addAttribute("carritoVacio", true);
        }

        return "carrito";
    }

    @PostMapping("/cambiar-cantidad")
    public String cambiarCantidadProducto(
            @RequestParam("id") Integer carritoProductoId,
            @RequestParam("accion") String accion) {
        Optional<CarritoProductos> opt = carritoProductosService.listaCarritoProducto(carritoProductoId);
        if (opt.isPresent()) {
            CarritoProductos cp = opt.get();
            int cantidadActual = cp.getCantidad();

            if ("incrementar".equals(accion)) {
                cp.setCantidad(cantidadActual + 1);
                carritoProductosService.guardarCarritoProducto(cp);
            } else if ("decrementar".equals(accion)) {
                if (cantidadActual > 1) {
                    cp.setCantidad(cantidadActual - 1);
                    carritoProductosService.guardarCarritoProducto(cp);
                } else {
                    carritoProductosService.eliminarCarritoProducto(carritoProductoId);
                }
            }
        }
        return "redirect:/carrito/ver";
    }

    @PostMapping("/eliminar-producto")
    public String eliminarProducto(@RequestParam("id") Integer carritoProductoId) {
        carritoProductosService.eliminarCarritoProducto(carritoProductoId);
        return "redirect:/carrito/ver";
    }

}
