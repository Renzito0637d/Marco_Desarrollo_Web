package com.urbanfeet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("admin/Almacen")
    public String getAlmacen() {
        return "Admin/Almacen";
    }

    @GetMapping("admin/Usuarios")
    public String getUsuarios() {
        return "Admin/Usuarios";
    }

    @GetMapping("admin/Pedidos")
    public String getOrdenes() {
        return "Admin/Pedidos";
    }
/* 
    @GetMapping("admin/Productos")
    public String getProductos() {
        return "Admin/Productos";
    }
        */
}
