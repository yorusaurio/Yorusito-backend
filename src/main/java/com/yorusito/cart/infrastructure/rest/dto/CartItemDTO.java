package com.yorusito.cart.infrastructure.rest.dto;

import com.yorusito.cart.domain.model.CartItem;
import com.yorusito.inventory.infrastructure.rest.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {

    private Long id;
    private ProductDTO product;  // Información esencial del producto
    private int quantity;

    // Conversión de entidad a DTO
    public static CartItemDTO fromEntity(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProduct(ProductDTO.fromEntity(item.getProduct()));  // Detalle del producto
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}
