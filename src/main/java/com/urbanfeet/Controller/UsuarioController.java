package com.urbanfeet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Usuario;
import com.urbanfeet.Entity.Rol;
import com.urbanfeet.Entity.Model.RegisterRequest;
import com.urbanfeet.Entity.Model.RegisterRequestAdmin;
import com.urbanfeet.Entity.Model.AuthResponse;
import com.urbanfeet.Service.UsuarioService;

import java.security.Principal;

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
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, @RequestParam("origen") String origen,
            Model model) {
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
        // return "redirect:/registro";
        if ("admin".equals(origen)) {
            return "redirect:/admin/Usuarios";
        } else {
            return "redirect:/IniciaSesion"; // o a donde quieras que vaya el usuario común
        }
    }
    /*
     * @PostMapping("/registroAdmin")
     * public ResponseEntity<AuthResponse> register(@RequestBody
     * RegisterRequestAdmin request) {
     * return ResponseEntity.ok(usuarioService.guardarUserAdmin(request));
     * }
     */

    @PostMapping("/registroAdmin")
    public String registrarAdminDesdeFormulario(@ModelAttribute("admin") RegisterRequestAdmin request) {
        usuarioService.guardarUserAdmin(request);
        return "redirect:/admin/Usuarios";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Admin/Usuarios";
    }

    @GetMapping("/inicio")
    public String sd(Model model) {
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Inicio";
    }

    @GetMapping("/Cuenta")
    public String mostrarCuenta(Model model, Principal principal) {
        Usuario usuario = usuarioService.obtenerPorEmail(principal.getName());

        if (usuario.getDireccion() == null) {
            usuario.setDireccion(new Direccion());
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("direccion", usuario.getDireccion());
        return "Cuenta";
    }

    @PostMapping("/perfil")
    public String actualizarPerfil(@ModelAttribute("usuario") Usuario usuarioForm, Principal principal) {
        usuarioService.actualizarDatosPersonales(principal.getName(), usuarioForm);
        return "redirect:/Cuenta?perfilActualizado";
    }

    @DeleteMapping("/admin/usuarios/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/usuario/{id}")
    @ResponseBody
    public Usuario obtenerUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Actualizar datos de un administrador
    @PostMapping("/admin/actualizarAdmin")
    @ResponseBody
    public ResponseEntity<?> actualizarAdmin(@RequestBody Usuario admin) {
        admin.setRol(Rol.ADMIN); // Asegurar el rol ADMIN
        usuarioService.actualizarUsuario(admin);
        return ResponseEntity.ok().build();
    }

    // Actualizar datos de un usuario común
    @PostMapping("/admin/actualizarUser")
    @ResponseBody
    public ResponseEntity<?> actualizarUser(@RequestBody Usuario user) {
        user.setRol(Rol.USER); // Asegurar el rol USER
        usuarioService.actualizarUsuario(user);
        return ResponseEntity.ok().build();
    }

}
