package com.yorusito.ordermanagement.infrastructure.rest.dto;

import com.yorusito.ordermanagement.domain.model.Order;
import com.yorusito.ordermanagement.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long customerId; // Solo necesitamos el ID del cliente
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItemDTO> items; // Lista de DTOs de los ítems de la orden
    private double totalPrice; // Precio total del pedido

    // Este método estático ayuda a convertir una entidad Order en OrderDTO
    public static OrderDTO fromEntity(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setItems(order.getItems().stream().map(OrderItemDTO::fromEntity).toList());
        return dto;
    }

    // Para convertir de DTO a entidad si lo necesitas
    public static Order toEntity(OrderDTO dto) {
        Order order = new Order();
        // Acá deberías de obtener el User desde el servicio si no tienes el objeto directo
        // order.setCustomer(userService.findById(dto.getCustomerId()).get());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setTotalPrice(dto.getTotalPrice());
        order.setItems(dto.getItems().stream().map(OrderItemDTO::toEntity).toList());
        return order;
    }
}
