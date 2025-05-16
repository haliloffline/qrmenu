package com.qrmenu.backend.repository;

import com.qrmenu.backend.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    List<MenuCategory> findByRestaurantIdOrderByDisplayOrderAsc(Long restaurantId);
}