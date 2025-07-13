package com.urbanfeet.Config;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthCustom implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        System.out.println("Roles del usuario autenticado: " + authorities);

        for (GrantedAuthority authority : authorities) {
            String rol = authority.getAuthority(); // Ejemplo: ROLE_ADMIN, ROLE_CLIENTE

            switch (rol) {
                case "ROLE_ADMIN":
                    response.sendRedirect("/admin/Productos");
                    return;
                case "ROLE_CLIENTE":
                case "ROLE_USER":
                    response.sendRedirect("/inicio");
                    return;
            }
        }

        // Redirección por defecto si el rol no coincide
        response.sendRedirect("/inicio");
    }
}
