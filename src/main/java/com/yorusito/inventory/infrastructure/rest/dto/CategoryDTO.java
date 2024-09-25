package com.yorusito.inventory.infrastructure.rest.dto;

import com.yorusito.inventory.domain.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;

    public static CategoryDTO fromEntity(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
