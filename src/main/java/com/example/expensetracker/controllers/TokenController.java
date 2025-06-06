package com.example.expensetracker.controllers;

import com.example.expensetracker.dto.TokenModel;
import com.example.expensetracker.service.TokenService;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.expensetracker.util.enums.ErrorCodes.getMessageByCode;

@RestController
@RequestMapping("/api/genToken")
public class TokenController {

    @Autowired
    private TokenService tokenService;

//    @GetMapping
//    public ApiBaseResponse<String> generateToken() {
//        try {
//            return ResponseUtil.successResp("ok", Jwts.builder().
//                    setSubject("token").setIssuer("ExpenseTracker").
//                    setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
//                    signWith(key).compact());
//        } catch (Exception e) {
//
//            return ResponseUtil.errorResp(500, getMessageByCode(500));
//        }
//
//    }

    @PostMapping
    public ApiBaseResponse<String> generateTokenPost(@RequestBody TokenModel tokenModel) {
        try {
            return tokenService.generateToken(tokenModel.getDeviceId());
        } catch (Exception e) {
            return ResponseUtil.errorResp(500, getMessageByCode(500));
        }

    }

}
