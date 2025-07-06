package com.example.expensetracker.interceptor;

import com.example.expensetracker.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Configuration
@EnableWebSecurity
public class SecurityInterceptor implements HandlerInterceptor {

    @Value("${auth.basic.username}")
    private String BASIC_AUTH_USERNAME;

    @Value("${auth.basic.password}")
    private String BASIC_AUTH_PASSWORD;

    public static boolean validateToken(HttpServletResponse response, String base64Token) throws IOException {
        try {
            Claims claim = Jwts.parserBuilder().setSigningKey(JwtUtil.getKey()).build().parseClaimsJws(base64Token).getBody();
            if (claim.getExpiration().before(new Date())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token.");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token.");
            return false;
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // or restrict as needed
                );

        return http.build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {

        System.out.println("[AUTH] Incoming request to: " + request.getRequestURI());
        System.out.println("[AUTH] Authorization header: " + handler);

        String requestURI = request.getRequestURI();
        // Custom logic: header check, token validation, logging, etc.
        String authHeader = request.getHeader("Authorization");

        // Skip authorization check for /api/genToken/** - For Basic Auth
        if (requestURI.endsWith("/api/genToken")) {
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

        return isAuthorized(request, response);
//        if (authHeader == null) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Missing or invalid Authorization header");
//            return false;
//        }
//
//        //Check for the Bearer Token
//        if (!authHeader.startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Missing or invalid Authorization header");
//            return false;
//        } else {
//            String base64Credentials = authHeader.split("Bearer ")[1];
//            boolean isValid = validateToken(response, base64Credentials);
//            return isValid;
//        }
    }

    private boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            unauthorized(response, "Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.substring(7); // Removes "Bearer " prefix
        return validateToken(response, token);
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(message);
    }

}

