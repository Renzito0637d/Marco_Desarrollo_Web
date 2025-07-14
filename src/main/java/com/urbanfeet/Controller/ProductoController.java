package com.urbanfeet.Controller;

import com.urbanfeet.Entity.Producto;
import com.urbanfeet.Repository.ProductoRepository;
import com.urbanfeet.Service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/Productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET: Mostrar página con productos
    @GetMapping
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos()); // usa el servicio
        model.addAttribute("producto", new Producto());
        return "Admin/Productos";
    }

    // POST: Guardar producto desde el formulario
    @PostMapping("/registrar")
    public String registrarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto); // usa el servicio
        return "redirect:/admin/Productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarPorId(id); // usa el servicio
        return "redirect:/admin/Productos";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto); // mismo método: guardar() sirve para crear o actualizar
        return "redirect:/admin/Productos";
    }
}