package com.urbanfeet.Repository;

import com.urbanfeet.Entity.ProductoVariacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoVariacionRepository extends JpaRepository<ProductoVariacion, Integer> {
    List<ProductoVariacion> findByProductoId(int productoId);

    List<ProductoVariacion> findByStockGreaterThan(int minStock);
}
