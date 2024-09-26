package com.yorusito.ordermanagement.infrastructure.rest.dto;

import com.yorusito.ordermanagement.domain.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private Long id;
    private Long productId; // Usar solo el ID del producto
    private int quantity;
    private double price;

    // Convertir de entidad OrderItem a DTO
    public static OrderItemDTO fromEntity(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        return dto;
    }

    // Convertir de DTO a entidad OrderItem
    public static OrderItem toEntity(OrderItemDTO dto) {
        OrderItem orderItem = new OrderItem();
        // Acá deberías de obtener el Product desde el servicio si no tienes el objeto directo
        // orderItem.setProduct(productService.findById(dto.getProductId()).get());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());
        return orderItem;
    }
}
