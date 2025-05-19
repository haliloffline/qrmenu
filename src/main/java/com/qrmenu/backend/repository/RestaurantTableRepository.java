package com.qrmenu.backend.repository;

import com.qrmenu.backend.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    boolean existsByCode(String code);
    List<RestaurantTable> findByRestaurantId(Long restaurantId);

    Optional<RestaurantTable> findByCode(String code);
}