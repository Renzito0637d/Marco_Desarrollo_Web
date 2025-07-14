package com.urbanfeet.Repository;

import com.urbanfeet.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /* Filtros más usados  */

    // Solo stock disponible
    Page<Producto> findByStockGreaterThan(int stock, Pageable pageable);

    // Búsqueda compuesta por marca y tipo (null-safe en la capa DAO)
    Page<Producto> findByMarcaIgnoreCaseAndTipoIgnoreCase(
            String marca, String tipo, Pageable pageable);

    // Rango de precios
    Page<Producto> findByPrecioBetween(
            double precioMin, double precioMax, Pageable pageable);
}
