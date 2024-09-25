package com.yorusito.cart.infrastructure.rest;

import com.yorusito.cart.domain.model.Cart;
import com.yorusito.cart.domain.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token); // Obtén el userId desde el token JWT
        Cart cart = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@RequestHeader("Authorization") String token,
                                                 @RequestParam Long productId,
                                                 @RequestParam int quantity) {
        Long userId = getUserIdFromToken(token);
        Cart cart = cartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok(cart);
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
        // Lógica para extraer el ID del usuario desde el token JWT
        // Puedes usar el jwtTokenProvider que ya tienes implementado
        return 1L; // Ejemplo ficticio
    }
}
