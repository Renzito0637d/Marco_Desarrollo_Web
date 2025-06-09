package com.urbanfeet.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String referencia;
}
