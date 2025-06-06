package com.example.expensetracker.service;

import com.example.expensetracker.util.constants.AppConstants;
import com.example.expensetracker.util.enums.ErrorCodes;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {
    private static final Key key = AppConstants.tokenKey;
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms

    public ApiBaseResponse<String> generateToken(String deviceId) {
        try {
            if (deviceId == null || deviceId.isEmpty()) {
                return ResponseUtil.errorResp(400, AppConstants.NO_DEVICE_ID_FOUND);
            }
            String token = Jwts.builder().
                    setSubject("token").setIssuer("ExpenseTracker").claim("deviceId", deviceId).
                    setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                    signWith(key).compact();
            return ResponseUtil.successResp("ok", token);
        } catch (Exception e) {
            // You can log the error for debugging
            e.printStackTrace(); // Or use a logger
            return ResponseUtil.errorResp(ErrorCodes.COMMON.getErrorCode(), AppConstants.FAILED_TO_GEN_TOKEN);
        }
    }
}
