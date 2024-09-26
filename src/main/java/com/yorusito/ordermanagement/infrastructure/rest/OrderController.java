package com.yorusito.ordermanagement.infrastructure.rest;

import com.yorusito.ordermanagement.domain.model.Order;
import com.yorusito.ordermanagement.domain.service.OrderService;
import com.yorusito.ordermanagement.infrastructure.rest.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders().stream().map(OrderDTO::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> ResponseEntity.ok(OrderDTO.fromEntity(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = OrderDTO.toEntity(orderDTO);
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(OrderDTO.fromEntity(savedOrder), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
