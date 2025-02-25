package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // 🔥 Désactive CSRF (utile pour tester les API REST)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll() // ✅ Autoriser tous les GET
                .requestMatchers(HttpMethod.POST, "/api/public/timeslots").permitAll() // ✅ Autoriser les POST
                .anyRequest().permitAll() // 🔥 Désactive l'authentification
            )
            .httpBasic(httpBasic -> {}) // Active Basic Auth (ne sert à rien ici car tout est ouvert)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // NoOp pour les tests (PAS EN PROD)
    }
}
