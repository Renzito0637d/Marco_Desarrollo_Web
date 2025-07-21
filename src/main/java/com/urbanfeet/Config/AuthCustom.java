package com.urbanfeet.Config;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import org.springframework.security.core.Authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthCustom implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, 
                                        Authentication authentication)
                                        throws IOException, ServletException {
        // Aquí obtenemos el rol del usuario desde el contexto de seguridad
        System.out.println("Autoridades del usuario: " + authentication.getAuthorities());

        String role = authentication.getAuthorities().toString();

        // Redirigir según el rol
        if (role.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/catalogo");  // Redirige al panel de administración
        } else {
            response.sendRedirect("/inicio");  // Redirige al panel de usuario
        }
    }
    
}
