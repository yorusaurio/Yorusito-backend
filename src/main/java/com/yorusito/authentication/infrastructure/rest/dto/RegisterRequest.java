package com.yorusito.authentication.infrastructure.rest.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
