package com.urbanfeet.Controller;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Model.RegisterRequestAdmin;
import com.urbanfeet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("admin/Almacen")
    public String getAlmacen() {
        return "Admin/Almacen";
    }

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

    @GetMapping("admin/Pedidos")
    public String getOrdenes() {
        return "Admin/Pedidos";
    }

    @GetMapping("admin/Productos")
    public String getProductos() {
        return "Admin/Productos";
    }

}
