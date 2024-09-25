package com.yorusito.inventory.domain.service;

import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Buscar producto por ID
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Guardar producto
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Eliminar producto por ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
