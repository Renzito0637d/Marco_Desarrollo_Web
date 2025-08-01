package com.urbanfeet.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    private final AuthCustom successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicEndpoints()).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/IniciaSesion")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/inicio")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));
        return httpSecurity.build();
    }

    private RequestMatcher publicEndpoints() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/IniciaSesion"),
                new AntPathRequestMatcher("/registrar"),
                new AntPathRequestMatcher("/inicio"),
                new AntPathRequestMatcher("/registro"),
                new AntPathRequestMatcher("/registroAdmin"),
                new AntPathRequestMatcher("/nosotros"),
                new AntPathRequestMatcher("/catalogo"),
                new AntPathRequestMatcher("/reclamos"),
                new AntPathRequestMatcher("/redes"),
                new AntPathRequestMatcher("/carrito"),
                new AntPathRequestMatcher("/miCuenta"),
                new AntPathRequestMatcher("/api/auth/**"),
                new AntPathRequestMatcher("/CSS/**"),
                new AntPathRequestMatcher("/IMG/**"),
                new AntPathRequestMatcher("/JS/**"),
                new AntPathRequestMatcher("/producto/**"));
    }
}