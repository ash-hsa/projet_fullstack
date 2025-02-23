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
                .requestMatchers("/public/**").permitAll() // Autorise certains endpoints
                .anyRequest().authenticated() // Auth obligatoire pour le reste
            )
            .httpBasic(httpBasic -> {}) // Active Basic Auth
            .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder pour les tests (ne pas utiliser en prod)
        return NoOpPasswordEncoder.getInstance();
    }
}