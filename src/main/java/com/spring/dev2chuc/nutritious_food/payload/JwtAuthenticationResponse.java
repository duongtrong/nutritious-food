package com.spring.dev2chuc.nutritious_food.payload;

public class JwtAuthenticationResponse {
    private int status;
    private String message;
    private String accessToken;

    public JwtAuthenticationResponse(int status, String message, String accessToken) {
        this.status = status;
        this.message = message;
        String tokenType = "Bearer";
        this.accessToken = tokenType + " " + accessToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
