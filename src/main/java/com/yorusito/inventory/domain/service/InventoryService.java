package com.yorusito.inventory.domain.service;

import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Verificar el stock de un producto
    public Optional<Integer> checkStock(Long productId) {
        return productRepository.findById(productId)
                .map(Product::getStock);
    }

    // Agregar stock a un producto
    public Optional<Product> addStock(Long productId, int quantity) {
        return productRepository.findById(productId).map(product -> {
            product.setStock(product.getStock() + quantity);
            return productRepository.save(product);
        });
    }

    // Reducir stock de un producto
    public Optional<Product> removeStock(Long productId, int quantity) {
        return productRepository.findById(productId).map(product -> {
            if (product.getStock() >= quantity) {
                product.setStock(product.getStock() - quantity);
                return productRepository.save(product);
            } else {
                throw new RuntimeException("Not enough stock available");
            }
        });
    }
}
