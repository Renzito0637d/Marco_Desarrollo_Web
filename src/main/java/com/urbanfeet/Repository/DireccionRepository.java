package com.urbanfeet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    
}
