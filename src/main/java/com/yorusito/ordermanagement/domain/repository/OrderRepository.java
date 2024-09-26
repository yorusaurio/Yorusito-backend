package com.yorusito.ordermanagement.domain.repository;

import com.yorusito.ordermanagement.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Obtener el número total de ventas
    @Query("SELECT SUM(o.totalPrice) FROM Order o")
    double getTotalSales();

    // Contar todas las órdenes
    long count();
}
