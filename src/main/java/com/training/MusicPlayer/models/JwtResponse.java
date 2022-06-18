package com.training.MusicPlayer.models;

public class JwtResponse {
    private String token;
    private static final String tokenType = "Bearer";
    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
