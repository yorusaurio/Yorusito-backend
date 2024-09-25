package com.yorusito.authentication.infrastructure.rest.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String role;
}
