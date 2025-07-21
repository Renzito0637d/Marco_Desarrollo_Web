package com.urbanfeet.Repository;

import com.urbanfeet.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Todos los filtros complejos deben ir al DAO o a consultas personalizadas
}
