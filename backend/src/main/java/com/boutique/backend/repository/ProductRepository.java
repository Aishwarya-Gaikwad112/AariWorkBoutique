package com.boutique.backend.repository;

import com.boutique.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query method to search by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
}
