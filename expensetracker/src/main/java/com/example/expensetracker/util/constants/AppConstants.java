package com.example.expensetracker.util.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;


public class AppConstants {

    // Token Gen Unique Key
    public static final Key tokenKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //Errors
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    // Prevent instantiation
    private AppConstants() {
    }

}
