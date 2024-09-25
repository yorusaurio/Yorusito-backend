package com.yorusito.cart.domain.repository;

import com.yorusito.cart.domain.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}