package com.example.expensetracker.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String requestURI = request.getRequestURI();
        // Custom logic: header check, token validation, logging, etc.
        String authHeader = request.getHeader("Authorization");

        // Skip authorization check for /api/auth/** and /api/genToken/**
        if (requestURI.startsWith("/api/auth/") || requestURI.startsWith("/api/genToken")) {
            return true; // Skip authorization check
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        // You can validate JWT here if needed


        return true; // continue processing
    }
}

