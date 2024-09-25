package com.yorusito.cart.domain.service;

import com.yorusito.cart.domain.model.Cart;
import com.yorusito.cart.domain.model.CartItem;
import com.yorusito.cart.domain.repository.CartRepository;
import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.service.ProductService;
import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    public CartService(CartRepository cartRepository, ProductService productService, UserService userService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public Cart getCartByUser(Long userId) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        return cartOpt.orElseGet(() -> {
            Cart newCart = new Cart();
            User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUser(userId);

        // Manejar Optional<Product>
        Product product = productService.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> cartItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getItems().add(cartItem);
        }

        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }

    public void checkout(Long userId) {
        Cart cart = getCartByUser(userId);
        // Procesa el pedido y vacía el carrito
        // Aquí puedes agregar la lógica para crear un pedido, cobrar al usuario, etc.
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
