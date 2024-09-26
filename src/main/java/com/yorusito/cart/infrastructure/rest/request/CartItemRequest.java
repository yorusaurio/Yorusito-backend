package com.yorusito.cart.infrastructure.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {

    private Long productId;  // Solo necesitamos el ID del producto para agregarlo
    private int quantity;    // Cantidad a agregar

}
