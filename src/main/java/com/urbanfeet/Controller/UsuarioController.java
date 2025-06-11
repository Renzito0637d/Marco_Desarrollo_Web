package com.urbanfeet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/IniciaSesion")
    public String newUsuario(Model model){
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Login";
    }

    @PostMapping("/registrar")
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuario.setRol(Rol.USER);
        usuarioService.guardarUsuario(usuario);    
        return "redirect:/registro";
    }

    @PostMapping("/login")
    public String loginUsuario(String email, String password, Model model) {
        Usuario usuario = usuarioService.autenticarUsuario(email, password);
        if (usuario != null) {
            // Usuario autenticado correctamente
            // Puedes guardar el usuario en sesión si lo deseas
            return "redirect:/inicio";
        } else {
            // Error de autenticación
            model.addAttribute("loginError", "Correo o contraseña incorrectos");
            return "Login";
        }
    }

    @GetMapping("/inicio")
    public String sd(Model model){
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Inicio";
    }
}
