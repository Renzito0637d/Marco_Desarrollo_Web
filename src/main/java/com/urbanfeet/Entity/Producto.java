package com.urbanfeet.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String marca;
    private String genero;
    private String tipo;

    // Relación uno a muchos con producto_variacion
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<ProductoVariacion> variaciones = new ArrayList<>();

    public int getStockTotal() {
        if (variaciones == null) return 0;
        return variaciones.stream()
                .mapToInt(v -> v.getStock() != null ? v.getStock() : 0)
                .sum();
    }

}
