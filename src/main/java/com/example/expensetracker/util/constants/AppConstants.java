package com.example.expensetracker.util.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;


public class AppConstants {

    // Token Gen Unique Key
    public static final Key tokenKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //Errors
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String NO_DEVICE_ID_FOUND = "No Device Id Found";
    public static final String FAILED_TO_GEN_TOKEN = "Failed to generate token";

    // Prevent instantiation
    private AppConstants() {
    }

}
