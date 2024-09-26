package com.yorusito.ordermanagement.domain.service;

import com.yorusito.ordermanagement.domain.model.Order;
import com.yorusito.ordermanagement.domain.model.OrderItem;
import com.yorusito.ordermanagement.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Método para calcular el precio total basado en los ítems
    public double calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        // Calcular el precio total antes de guardar
        double totalPrice = calculateTotalPrice(order.getItems());
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}