package com.example.expensetracker.util;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtUtil {
    // Token Gen Unique Key
    private static final String tokenKey = "qNBfAmnO5yVDcU6S9FnYfH2yOt3yX9S5TfFqTq4Og3c=";

    public static SecretKey getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(tokenKey));
    }
}
