package com.springbootblog.springbootrestapi.security;

public class JwtAuthResponse {
    private String accesToken;
    private String tokenType = "Bearer";

    public JwtAuthResponse(String accesToken) {
        this.accesToken = accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
