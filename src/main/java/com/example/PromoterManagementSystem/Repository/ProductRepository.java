package com.example.PromoterManagementSystem.Repository;

import com.example.PromoterManagementSystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
