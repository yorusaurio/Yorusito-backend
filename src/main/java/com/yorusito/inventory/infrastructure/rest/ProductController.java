package com.yorusito.inventory.infrastructure.rest;

import com.yorusito.inventory.domain.model.Category;
import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.service.CategoryService;
import com.yorusito.inventory.domain.service.ProductService;
import com.yorusito.inventory.infrastructure.rest.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService ) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(value -> ResponseEntity.ok(ProductDTO.fromEntity(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = ProductDTO.toEntity(productDTO, category);
        Product createdProduct = productService.saveProduct(ProductDTO.fromEntity(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO.fromEntity(createdProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> productOpt = productService.findById(id);
        if (productOpt.isPresent()) {
            Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            Product updatedProduct = ProductDTO.toEntity(productDTO, category);
            productService.saveProduct(ProductDTO.fromEntity(updatedProduct));
            return ResponseEntity.ok(ProductDTO.fromEntity(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
