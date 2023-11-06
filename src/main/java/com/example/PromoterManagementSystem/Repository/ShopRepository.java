package com.example.PromoterManagementSystem.Repository;

import com.example.PromoterManagementSystem.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
