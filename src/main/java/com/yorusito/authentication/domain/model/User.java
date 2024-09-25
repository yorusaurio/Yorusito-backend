package com.yorusito.authentication.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yorusito.customer.domain.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // Ej. "ROLE_ADMIN" o "ROLE_CUSTOMER"

    // Relación con el cliente (puede ser opcional para los usuarios no clientes)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference // Esto evita la serialización infinita en el lado del user
    private Customer customer;
}
