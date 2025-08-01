package com.urbanfeet.Entity.Model;

import com.urbanfeet.Entity.Direccion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestAdmin {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String password;
    private Direccion direccion;
}
