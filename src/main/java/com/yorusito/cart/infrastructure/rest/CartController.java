package com.yorusito.cart.infrastructure.rest;

import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.service.UserService;
import com.yorusito.authentication.infrastructure.security.JwtTokenProvider;
import com.yorusito.cart.domain.model.Cart;
import com.yorusito.cart.domain.service.CartService;
import com.yorusito.cart.infrastructure.rest.dto.CartDTO;
import com.yorusito.cart.infrastructure.rest.request.CartItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService; // Necesitas el servicio de usuario

    public CartController(CartService cartService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.cartService = cartService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(@RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token);
        Cart cart = cartService.getCartByUser(userId);
        return ResponseEntity.ok(CartDTO.fromEntity(cart));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addProductToCart(@RequestHeader("Authorization") String token,
                                                    @RequestBody CartItemRequest addProductRequest) {
        Long userId = getUserIdFromToken(token);
        Cart cart = cartService.addProductToCart(userId, addProductRequest.getProductId(), addProductRequest.getQuantity());
        return ResponseEntity.ok(CartDTO.fromEntity(cart));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@RequestHeader("Authorization") String token,
                                                      @PathVariable Long productId) {
        Long userId = getUserIdFromToken(token);
        Cart cart = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token);
        cartService.checkout(userId);
        return ResponseEntity.ok("Order processed successfully.");
    }

    private Long getUserIdFromToken(String token) {
        String cleanedToken = token.replace("Bearer ", "");
        String username = jwtTokenProvider.getUsernameFromToken(cleanedToken);
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
