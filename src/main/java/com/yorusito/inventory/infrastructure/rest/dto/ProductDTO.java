package com.yorusito.inventory.infrastructure.rest.dto;

import com.yorusito.inventory.domain.model.Category;
import com.yorusito.inventory.domain.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;  // Solo el ID de la categoría

    // Convertir de entidad Product a DTO
    public static ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategoryId(product.getCategory().getId());  // Solo el ID de la categoría
        return dto;
    }

    // Convertir de DTO a entidad Product
    public static Product toEntity(ProductDTO dto, Category category) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(category);  // Asignar la categoría
        return product;
    }
}
