package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Désactive CSRF (utile pour les tests d'API)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll() // ✅ Autoriser tous les endpoints publics
                .anyRequest().authenticated() // Auth obligatoire pour le reste
            )
            .httpBasic(httpBasic -> {}) // Active Basic Auth
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // NoOp pour les tests (PAS EN PROD)
    }
}
