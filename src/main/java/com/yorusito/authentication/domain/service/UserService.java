package com.yorusito.authentication.domain.service;

import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Guardar un usuario
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Buscar usuario por su ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Buscar usuario por su username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Eliminar un usuario por su ID
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Obtener todos los usuarios
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Comprobar si un usuario existe por su ID
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    // Actualizar el rol de un usuario
    public User updateUserRole(Long id, String newRole) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setRole(newRole);
        return userRepository.save(user);
    }
}
