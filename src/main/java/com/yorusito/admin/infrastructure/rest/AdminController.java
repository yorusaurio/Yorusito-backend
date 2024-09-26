package com.yorusito.admin.infrastructure.rest;

import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.service.UserService;
import com.yorusito.admin.domain.service.AdminService;
import com.yorusito.admin.domain.model.Statistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    // Obtener la lista de todos los usuarios registrados
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener estadísticas generales de ventas y productos
    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getStatistics() {
        Statistics statistics = adminService.getStatistics();
        return ResponseEntity.ok(statistics);
    }
}
