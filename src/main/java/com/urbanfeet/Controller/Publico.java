package com.urbanfeet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Entity.Usuario;

@Controller
public class Publico {
    @GetMapping("/nosotros")
    public String nosotros(Model model){
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "MyV";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model){
        Usuario usuario = new Usuario();
        usuario.setDireccion(new Direccion());
        model.addAttribute("usuario", usuario);
        return "Catalogo";
    }

    @GetMapping("/reclamos")
    public String reclamos(){
        return "Reclamos";
    }

    @GetMapping("/redes")
    public String redes(){
        return "Redes";
    }

    @GetMapping("/carrito")
    public String carrito(){
        return "carrito";
    }

    @GetMapping("/miCuenta")
    public String miCuenta(){
        return "Cuenta";
    }

    @GetMapping("/MisPedidos")
    public String misPedidos() {
        return "MisPedidos";
    }
}
