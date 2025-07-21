package com.urbanfeet.Repository;

import com.urbanfeet.Entity.ProductoVariacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoVariacionRepository extends JpaRepository<ProductoVariacion, Integer> {
    List<ProductoVariacion> findByProductoId(int productoId);

    List<ProductoVariacion> findByStockGreaterThan(int minStock);

    Optional<ProductoVariacion> findByProductoIdAndColor(Integer productoId, String color);
}
