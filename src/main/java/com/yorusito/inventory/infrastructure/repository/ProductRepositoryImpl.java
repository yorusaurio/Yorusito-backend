package com.yorusito.inventory.infrastructure.repository;

import com.yorusito.inventory.domain.model.Product;
import com.yorusito.inventory.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryImpl extends JpaRepository<Product, Long>, ProductRepository {

}