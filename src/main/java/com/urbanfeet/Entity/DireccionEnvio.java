package com.urbanfeet.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionEnvio {
    private String calle;
    private String distrito;
    private String provincia;
    private String departamento;
    private String referencia;
}
