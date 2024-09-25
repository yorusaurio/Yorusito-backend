package com.yorusito.ordermanagement.domain.repository;

import com.yorusito.ordermanagement.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Order save(Order order);
    void deleteById(Long id);
}