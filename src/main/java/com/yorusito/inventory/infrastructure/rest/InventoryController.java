package com.yorusito.inventory.infrastructure.rest;

import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Verificar el stock de un producto
    @GetMapping("/{productId}")
    public ResponseEntity<Integer> checkStock(@PathVariable Long productId) {
        Optional<Integer> stock = inventoryService.checkStock(productId);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Agregar stock a un producto
    @PostMapping("/{productId}/add")
    public ResponseEntity<Product> addStock(@PathVariable Long productId, @RequestParam int quantity) {
        Optional<Product> product = inventoryService.addStock(productId, quantity);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Reducir stock de un producto
    @PostMapping("/{productId}/remove")
    public ResponseEntity<Product> removeStock(@PathVariable Long productId, @RequestParam int quantity) {
        try {
            Optional<Product> product = inventoryService.removeStock(productId, quantity);
            return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Maneja casos donde no hay suficiente stock
        }
    }
}
