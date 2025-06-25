package com.urbanfeet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    
}
