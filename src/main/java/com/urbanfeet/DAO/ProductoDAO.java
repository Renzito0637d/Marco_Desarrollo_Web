package com.urbanfeet.DAO;

import com.urbanfeet.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductoDAO {

    Page<Producto> filtrarCatalogo(
            List<String> marcas,
            String genero,
            String tipo,
            String color,
            Double precioMin,
            Double precioMax,
            boolean soloDisponibles,
            Pageable pageable);

    Optional<Producto> buscarPorId(int id);

    Producto guardar(Producto producto);

    void eliminar(int id);
}
