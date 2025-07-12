package com.urbanfeet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Publico {
    @GetMapping("/nosotros")
    public String nosotros(){
        return "MyV";
    }

    @GetMapping("/catalogo")
    public String catalogo(){
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
