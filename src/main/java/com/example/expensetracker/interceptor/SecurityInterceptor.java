package com.example.expensetracker.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityInterceptor implements HandlerInterceptor {

    @Value("${auth.basic.username}")
    private String BASIC_AUTH_USERNAME;

    @Value("${auth.basic.password}")
    private String BASIC_AUTH_PASSWORD;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // or restrict as needed
                );

        return http.build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("[AUTH] Incoming request to: " + request.getRequestURI());
        System.out.println("[AUTH] Authorization header: " + handler);

        String requestURI = request.getRequestURI();
        // Custom logic: header check, token validation, logging, etc.
        String authHeader = request.getHeader("Authorization");

        // Skip authorization check for /api/genToken/** - For Basic Auth
        if (requestURI.startsWith("/api/genToken")) {
            if (authHeader == null || !authHeader.startsWith("Basic ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Missing Basic Authorization header");
                return false;
            }
            // Decode and validate
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":", 2);

            if (parts.length != 2 || !parts[0].equals(BASIC_AUTH_USERNAME) || !parts[1].equals(BASIC_AUTH_PASSWORD)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid Basic Auth credentials");
                return false;
            }

            return true;
        }

        //Check for the Bearer Token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        // You can validate JWT here if needed


        return true; // continue processing
    }
}

