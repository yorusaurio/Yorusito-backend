package com.yorusito.ordermanagement.domain.model;

import com.yorusito.authentication.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    // Agregar el campo totalPrice
    private double totalPrice;

    // Constructor vacío
    public Order() {}

    // Constructor con parámetros
    public Order(User customer, LocalDateTime orderDate, OrderStatus status, List<OrderItem> items, double totalPrice) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
