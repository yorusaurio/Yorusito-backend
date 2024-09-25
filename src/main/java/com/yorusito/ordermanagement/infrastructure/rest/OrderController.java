package com.yorusito.ordermanagement.infrastructure.rest;

import com.yorusito.ordermanagement.domain.model.Order;
import com.yorusito.ordermanagement.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Obtener todos los pedidos")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Obtener un pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo pedido")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un pedido por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}