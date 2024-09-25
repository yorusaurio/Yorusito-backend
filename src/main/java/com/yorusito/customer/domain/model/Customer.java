package com.yorusito.customer.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yorusito.authentication.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Relación con la entidad `User`
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference // Esto maneja la serialización desde el lado del customer
    private User user;  // Asociamos el cliente con el usuario
}
