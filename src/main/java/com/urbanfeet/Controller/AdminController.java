package com.urbanfeet.Controller;


import org.springframework.security.access.prepost.PreAuthorize;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Model.RegisterRequestAdmin;
import com.urbanfeet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("admin/Usuarios")
    public String getUsuarios(Model model) {
        model.addAttribute("admins", usuarioService.obtenerUsuariosPorRol("ADMIN"));
        model.addAttribute("users", usuarioService.obtenerUsuariosPorRol("USER"));

        RegisterRequestAdmin admin = new RegisterRequestAdmin();
        admin.setDireccion(new Direccion());
        model.addAttribute("admin", admin);

        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        
        return "Admin/Usuarios";
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
