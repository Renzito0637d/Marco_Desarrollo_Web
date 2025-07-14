package com.urbanfeet.Service;

import com.urbanfeet.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {

    Page<Producto> listarCatalogo(
            List<String> marcas,      // ←  ahora lista
            String genero,
            String tipo,
            String color,
            Double precioMin,
            Double precioMax,
            boolean soloDisponibles,
            Pageable pageable);

    Producto obtener(int id);

    Producto crear(Producto producto);

    Producto actualizar(int id, Producto producto);

    void eliminar(int id);
}
