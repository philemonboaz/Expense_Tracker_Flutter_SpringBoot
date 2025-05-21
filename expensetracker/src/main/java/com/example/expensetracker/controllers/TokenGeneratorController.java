package com.example.expensetracker.controllers;

import com.example.expensetracker.util.constants.AppConstants;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;

import static com.example.expensetracker.util.enums.ErrorCodes.getMessageByCode;

@RestController
@RequestMapping("/api/genToken")
public class TokenGeneratorController {
    private static final Key key = AppConstants.tokenKey;
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms

    @GetMapping
    public static ApiBaseResponse<String> generateToken() {
        try {
            return ResponseUtil.successResp("ok", Jwts.builder().
                    setSubject("token").setIssuer("ExpenseTracker").
                    setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                    signWith(key).compact());
        } catch (Exception e) {

            return ResponseUtil.errorResp(500, getMessageByCode(500));
        }

    }

}
