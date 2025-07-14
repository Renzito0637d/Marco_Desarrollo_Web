package com.urbanfeet.Controller;

import com.urbanfeet.Entity.Producto;
import com.urbanfeet.Service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/catalogo")
@PreAuthorize("hasRole('ADMIN')")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Listar productos para los usuarios y administradores
    @GetMapping
    public String listarProductos(@RequestParam(required = false) List<String> marcas,
                                  @RequestParam(required = false) String genero,
                                  @RequestParam(required = false) String tipo,
                                  @RequestParam(required = false) String color,
                                  @RequestParam(required = false) Double precioMin,
                                  @RequestParam(required = false) Double precioMax,
                                  @RequestParam(defaultValue = "false") boolean disponibles,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
        var productos = productoService.listarCatalogo(
                marcas, genero, tipo, color,
                precioMin, precioMax,
                disponibles,
                PageRequest.of(page, size)
        );
        model.addAttribute("productos", productos);
        return "admin/catalogo/AdminCatalogo";
    }

    // Crear un nuevo producto (solo para admin)
    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public String formNuevoProducto(Model model) {
        List<String> tipo = Arrays.asList("Running", "Futbol", "Training", "Outdoor", "Sneakers", "Lifestyle");
        model.addAttribute("tipo", tipo);  // Pasa la lista de tipos a Thymeleaf
        model.addAttribute("producto", new Producto());
        return "admin/catalogo/ProductoNuevo";
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public String crearProducto(@Valid @ModelAttribute Producto producto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("producto", producto);
            model.addAttribute("error", "Por favor, revise los campos del producto.");
            return "admin/catalogo/ProductoNuevo";
        }
        try {
            productoService.crear(producto);
        } catch (Exception e) {
            // En caso de un error inesperado, pasa el mensaje del error a la vista
            model.addAttribute("producto", producto);
            model.addAttribute("error", "Hubo un error al crear el producto: " + e.getMessage());
            return "admin/catalogo/ProductoNuevo";  // Redirige a la misma página de crear producto
        }
        return "redirect:/admin/catalogo";
    }

    // Editar producto (solo para admin)
    @GetMapping("/{id}/editar")
    @PreAuthorize("hasRole('ADMIN')")
    public String formEditarProducto(@PathVariable int id, Model model) {
        Producto producto = productoService.obtener(id);  // Obtener producto por ID
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        model.addAttribute("producto", producto);         // Pasarlo al modelo
        return "admin/catalogo/ProductoEditar";             // La vista a cargar
    }

    @PostMapping("/{id}/editar")
    @PreAuthorize("hasRole('ADMIN')")
    public String actualizarProducto(@PathVariable int id, @Valid @ModelAttribute Producto producto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("producto", producto);
            model.addAttribute("error", "Por favor, revise los campos del producto.");
            return "admin/catalogo/ProductoEditar";
        }
        try {
            productoService.actualizar(id, producto);
        } catch (Exception e) {
            model.addAttribute("producto", producto);
            model.addAttribute("error", "Hubo un error al editar el producto: " + e.getMessage());
            return "admin/catalogo/ProductoEditar";  // Redirige a la página de editar producto
        }
        return "redirect:/admin/catalogo";
    }

    // Ver detalle del producto (disponible para todos los usuarios)
    @GetMapping("/{id}")
    public String verProducto(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoService.obtener(id));
        return "public/producto-detalle";
    }

    // Eliminar producto (solo para admin)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String eliminarProducto(@PathVariable int id) {
        productoService.eliminar(id);
        return "redirect:/admin/catalogo";
    }
}
