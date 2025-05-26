package com.example.expensetracker.service.class_impl;

import com.example.expensetracker.util.constants.AppConstants;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final Key key = AppConstants.tokenKey;
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms


    public ApiBaseResponse<String> createToken(String deviceId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("deviceId", deviceId); // Add custom claim
        return ResponseUtil.successResp("ok", Jwts.builder().addClaims(claims).
                setSubject("Token").setIssuer("ExpenseTracker").
                setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                signWith(key).compact());
    }
}
