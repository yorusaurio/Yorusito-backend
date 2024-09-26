package com.yorusito.cart.infrastructure.rest.dto;


import com.yorusito.authentication.infrastructure.rest.dto.UserDTO;
import com.yorusito.cart.domain.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartDTO {

    private Long id;
    private UserDTO user;  // Solo los datos esenciales del usuario
    private List<CartItemDTO> items;  // Lista de los items en el carrito

    // Conversión de entidad a DTO
    public static CartDTO fromEntity(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUser(UserDTO.fromEntity(cart.getUser()));  // Solo datos clave del usuario
        dto.setItems(cart.getItems().stream()
                .map(CartItemDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }
}
