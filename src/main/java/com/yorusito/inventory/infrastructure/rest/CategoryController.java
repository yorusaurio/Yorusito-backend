package com.yorusito.inventory.infrastructure.rest;

import com.yorusito.inventory.domain.model.Category;
import com.yorusito.inventory.domain.service.CategoryService;
import com.yorusito.inventory.infrastructure.rest.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDTOS);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName());
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(CategoryDTO.fromEntity(savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.getCategoryById(id)
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    Category updatedCategory = categoryService.saveCategory(category);
                    return ResponseEntity.ok(CategoryDTO.fromEntity(updatedCategory));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> {
                    categoryService.deleteCategory(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
