package com.urbanfeet.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/pedidos")
    public String verPedidos() {
        return "admin/Pedidos"; // templates/admin/Pedidos.html
    }

    @GetMapping("/productos")
    public String verProductos() {
        return "admin/Productos"; // templates/admin/Productos.html
    }

    @GetMapping("/usuarios")
    public String verUsuarios() {
        return "admin/Usuarios"; // templates/admin/Usuarios.html
    }

    @GetMapping("/almacen")
    public String verAlmacen() {
        return "admin/Almacen"; // templates/admin/Almacen.html
    }
}
