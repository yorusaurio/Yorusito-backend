package com.yorusito.ordermanagement.infrastructure.repository;

import com.yorusito.ordermanagement.domain.model.Order;
import com.yorusito.ordermanagement.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryImpl extends JpaRepository<Order, Long>, OrderRepository {
    // Métodos adicionales si es necesario
}