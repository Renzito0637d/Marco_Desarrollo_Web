package com.urbanfeet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Model.RegisterRequest;
import com.urbanfeet.Entity.Model.AuthResponse;
import com.urbanfeet.Service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/IniciaSesion")
    public String newUsuario(Model model){
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Login";
    }

    @PostMapping("/registrar")
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        // Construir RegisterRequest a partir del formulario
        RegisterRequest request = RegisterRequest.builder()
            .nombre(usuario.getNombre())
            .apellido(usuario.getApellido())
            .email(usuario.getEmail())
            .telefono(usuario.getTelefono())
            .password(usuario.getPassword())
            .build();

        AuthResponse response = usuarioService.guardarUsuario(request);
        // Puedes guardar el token en sesión o mostrarlo en la vista si lo deseas
        // model.addAttribute("jwt", response.getToken());
        return "redirect:/registro";
    }

    @GetMapping("/inicio")
    public String sd(Model model){
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Inicio";
    }
}
