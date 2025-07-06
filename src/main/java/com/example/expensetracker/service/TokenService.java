package com.example.expensetracker.service;

import com.example.expensetracker.entity.InitDataEntity;
import com.example.expensetracker.repository.InitDataRepository;
import com.example.expensetracker.util.JwtUtil;
import com.example.expensetracker.util.constants.AppConstants;
import com.example.expensetracker.util.enums.ErrorCodes;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {
    private static final SecretKey key = JwtUtil.getKey();
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms
    @Autowired
    private InitDataRepository initRepository;

    public ApiBaseResponse<String> generateToken(String deviceId) {
        try {
            if (deviceId == null || deviceId.isEmpty()) {
                return ResponseUtil.errorResp(400, AppConstants.NO_DEVICE_ID_FOUND);
            }

            String token = Jwts.builder().
                    setSubject("token").setIssuer("ExpenseTracker").claim("deviceId", deviceId).
                    setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                    signWith(key).compact();

            // To check for the user availability in the DB
            InitDataEntity isDataAvailable = initRepository.getDeviceIdFromDb(deviceId);
            if (isDataAvailable != null) {
                setJwtTokenToDB(isDataAvailable.getDeviceId(), token);
            } else {
                initRepository.insertInitData(deviceId, generateUniqueId(deviceId), token);
            }

            return ResponseUtil.successResp("ok", token);
        } catch (Exception e) {
            // You can log the error for debugging
            e.printStackTrace(); // Or use a logger
            return ResponseUtil.errorResp(ErrorCodes.COMMON.getErrorCode(), e.getMessage());
        }
    }

    // Change of jwt token on init everytime.
    public void setJwtTokenToDB(String deviceId, String jwtToken) {
        try {
            initRepository.updateInitJwt(deviceId, jwtToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // For new user generation od uniqueId
    public String generateUniqueId(String deviceId) {
        Date currDate = new Date();
        return (deviceId + currDate).replaceAll(" ", "");
    }


}
