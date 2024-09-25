package com.yorusito.authentication.domain.repository;

import com.yorusito.authentication.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método personalizado para buscar usuario por su nombre de usuario
    Optional<User> findByUsername(String username);
}