package com.urbanfeet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.CarritoProductos;

@Repository
public interface CarritoProductosRepository extends JpaRepository<CarritoProductos, Integer> {
    
}
