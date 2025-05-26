package com.example.expensetracker.controllers;

import com.example.expensetracker.dto.JwtRequest;
import com.example.expensetracker.service.class_impl.JwtService;
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
public class TokenGeneratorController {


    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtRequest jwtRequest;


    @PostMapping
    public ApiBaseResponse<String> generateToken(@RequestBody JwtRequest request) {
        try {
            return jwtService.createToken(request.getDeviceId());
        } catch (Exception e) {
            return ResponseUtil.errorResp(500, getMessageByCode(500));
        }

    }

}
