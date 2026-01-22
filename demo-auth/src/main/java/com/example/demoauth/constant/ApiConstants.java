package com.example.demoauth.constant;

public class ApiConstants {
    public static final String API_V1 = "/api/v1";
    
    // Internal path
    public static final String INTERNAL = API_V1 + "/internal";
    public static final String AUTH_INTERNAL = INTERNAL + "/auth";

    // Service path
    public static final String AUTH = API_V1 + "/auth";
    
    // Sub-paths for Auth service
    public static final String AUTH_USER = AUTH + "/user";
    public static final String AUTH_ADMIN = AUTH + "/admin";
    public static final String AUTH_PUBLIC = AUTH + "/public";
    
    // Specific paths
    public static final String JWKS = AUTH + "/.well-known/jwks.json";
}
