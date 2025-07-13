package com.urbanfeet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Model.RegisterRequest;
import com.urbanfeet.Entity.Model.RegisterRequestAdmin;
import com.urbanfeet.Entity.Model.AuthResponse;
import com.urbanfeet.Service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/IniciaSesion")
    public String newUsuario(Model model) {
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
                .direccion(usuario.getDireccion())
                .build();

        AuthResponse response = usuarioService.guardarUsuario(request);
        // Puedes guardar el token en sesión o mostrarlo en la vista si lo deseas
        // model.addAttribute("jwt", response.getToken());
        return "redirect:/registro";
    }

    @PostMapping("/registroAdmin")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestAdmin request) {
        return ResponseEntity.ok(usuarioService.guardarUserAdmin(request));
    }

    @GetMapping("/inicio")
    public String sd(Model model) {
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Inicio";
    }

    @GetMapping("/api/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @DeleteMapping("/api/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/usuarios/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            usuario.setId(id); // Asegura que se use el ID de la URL
            usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok("Usuario actualizado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar usuario.");
        }
    }

    @PostMapping("/registrarCliente")
    public ResponseEntity<AuthResponse> registrarCliente(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = usuarioService.guardarUsuario(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
