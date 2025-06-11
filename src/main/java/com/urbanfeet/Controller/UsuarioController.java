package com.urbanfeet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String newUsuario(Model model){
        Usuario usuario = new Usuario();
        Direccion direccion = new Direccion();
        model.addAttribute("usuario", usuario);
        model.addAttribute("direccion", direccion);
        return "Login";
    }

    @GetMapping("/registrar")
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, @ModelAttribute("direccion") Direccion direccion) {
        usuario.setDireccion(direccion);
        usuario.setRol(Rol.USER);
        usuarioService.guardarUsuario(usuario);    
        return "redirect:/Login";
    }
    
}
