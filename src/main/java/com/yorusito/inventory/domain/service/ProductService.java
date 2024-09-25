package com.yorusito.inventory.domain.service;

import com.yorusito.inventory.domain.model.Category;
import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.repository.ProductRepository;
import com.yorusito.inventory.infrastructure.rest.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    // Buscar producto por ID
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Guardar o actualizar un producto usando ProductDTO
    public Product saveProduct(ProductDTO productDTO) {
        // Obtener la categoría por su ID
        Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Convertir ProductDTO a Product y asignar la categoría
        Product product = ProductDTO.toEntity(productDTO, category);

        // Guardar el producto en la base de datos
        return productRepository.save(product);
    }

    // Eliminar un producto por ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
