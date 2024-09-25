package com.yorusito.authentication.infrastructure.repository;

import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Long>, UserRepository {
    Optional<User> findByUsername(String username);
}