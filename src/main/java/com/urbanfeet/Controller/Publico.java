package com.urbanfeet.Controller;

import com.urbanfeet.Entity.Producto;
import com.urbanfeet.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class Publico {

    private final ProductoService productoService;

    @GetMapping("/nosotros")
    public String nosotros() {
        return "MyV";
    }

    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(required = false) List<String> marcas,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String color,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ) {
        Page<Producto> productos = productoService.listarCatalogo(
                marcas, genero, tipo, color, null, null, true,
                PageRequest.of(page, size)
        );

        model.addAttribute("productos", productos);
        model.addAttribute("marcas", marcas);
        model.addAttribute("genero", genero);
        model.addAttribute("tipo", tipo);
        model.addAttribute("color", color);
        return "Catalogo";
    }

    @GetMapping("/producto/{id}")
    public String detalleProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.obtener(id);
        model.addAttribute("producto", producto);
        return "producto-detalle"; 
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
