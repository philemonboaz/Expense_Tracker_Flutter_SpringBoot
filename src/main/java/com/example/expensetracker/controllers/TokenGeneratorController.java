package com.example.expensetracker.controllers;

import com.example.expensetracker.common.constants.AppConstants;
import com.example.expensetracker.common.constants.ErrorCodes;
import com.example.expensetracker.common.response.ApiResponse;
import com.example.expensetracker.common.response.ResponseUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;

@RestController
@RequestMapping("/api/genToken")
public class TokenGeneratorController {
    private static final Key key = AppConstants.tokenKey;
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms

    @GetMapping
    public static ApiResponse generateToken() {
        try {
            return ResponseUtil.successResp("ok", Jwts.builder().
                    setSubject("token").setIssuer("ExpenseTracker").
                    setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                    signWith(key).compact());
        } catch (Exception e) {
            ErrorCodes data = ErrorCodes.BACKEND_ERROR;
            return ResponseUtil.errorResp(data.getErrorCode(), data.getErrorMessage());
        }

    }

}
