package com.yorusito.authentication.infrastructure.rest.dto;

import com.yorusito.authentication.domain.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String role;

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}
