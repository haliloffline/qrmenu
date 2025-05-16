package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.MenuCategoryCreateDto;
import com.qrmenu.backend.dto.MenuCategoryDto;
import com.qrmenu.backend.entity.MenuCategory;
import com.qrmenu.backend.entity.Restaurant;
import com.qrmenu.backend.mapper.MenuCategoryMapper;
import com.qrmenu.backend.repository.MenuCategoryRepository;
import com.qrmenu.backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuCategoryMapper mapper;

    public MenuCategoryService(MenuCategoryRepository categoryRepository,
                               RestaurantRepository restaurantRepository,
                               MenuCategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    public List<MenuCategoryDto> getByRestaurantId(Long restaurantId) {
        return categoryRepository.findByRestaurantIdOrderByDisplayOrderAsc(restaurantId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MenuCategoryDto> getById(Long id) {
        return categoryRepository.findById(id).map(mapper::toDto);
    }

    public MenuCategoryDto create(MenuCategoryCreateDto dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restoran bulunamadı"));

        MenuCategory category = mapper.toEntity(dto);
        category.setRestaurant(restaurant);

        return mapper.toDto(categoryRepository.save(category));
    }

    public MenuCategoryDto update(Long id, MenuCategoryCreateDto dto) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setDisplayOrder(dto.getDisplayOrder());

            Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                    .orElseThrow(() -> new RuntimeException("Restoran bulunamadı"));
            existing.setRestaurant(restaurant);

            return mapper.toDto(categoryRepository.save(existing));
        }).orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}