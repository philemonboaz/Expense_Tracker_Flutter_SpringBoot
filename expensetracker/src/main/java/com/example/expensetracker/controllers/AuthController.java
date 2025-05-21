package com.example.expensetracker.controllers;

import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/init")
public class AuthController {

    @GetMapping
    public static ApiBaseResponse<Object> initUserId() {
        return ResponseUtil.successResp("", "asdfcv");
    }
}
