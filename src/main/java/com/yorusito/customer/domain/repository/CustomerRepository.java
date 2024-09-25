package com.yorusito.customer.domain.repository;

import com.yorusito.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Métodos personalizados pueden ir aquí si es necesario
}
