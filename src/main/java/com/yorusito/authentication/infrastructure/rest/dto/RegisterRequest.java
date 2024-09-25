package com.yorusito.authentication.infrastructure.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String password;

    // Información adicional para clientes
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
