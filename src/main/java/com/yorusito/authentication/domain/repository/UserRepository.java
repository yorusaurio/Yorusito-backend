package com.yorusito.authentication.domain.repository;

import com.yorusito.authentication.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    User save(User user);
}