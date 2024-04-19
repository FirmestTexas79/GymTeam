package com.gymos.web.dto;

import lombok.Data;

@Data
public class AuthResponceDto {
    private String accessToken;
    private String tokenType;

    public AuthResponceDto(String accessToken){
        this.accessToken = accessToken;
    }
}
