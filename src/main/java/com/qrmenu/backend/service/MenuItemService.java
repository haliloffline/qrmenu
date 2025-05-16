package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.MenuItemCreateDto;
import com.qrmenu.backend.dto.MenuItemDto;
import com.qrmenu.backend.entity.MenuItem;
import com.qrmenu.backend.entity.MenuCategory;
import com.qrmenu.backend.mapper.MenuItemMapper;
import com.qrmenu.backend.repository.MenuCategoryRepository;
import com.qrmenu.backend.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    private final MenuItemRepository itemRepository;
    private final MenuCategoryRepository categoryRepository;
    private final MenuItemMapper mapper;

    public MenuItemService(MenuItemRepository itemRepository,
                           MenuCategoryRepository categoryRepository,
                           MenuItemMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<MenuItemDto> getByCategoryId(Long categoryId) {
        return itemRepository.findByCategoryIdOrderByIdAsc(categoryId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MenuItemDto> getById(Long id) {
        return itemRepository.findById(id).map(mapper::toDto);
    }

    public MenuItemDto create(MenuItemCreateDto dto) {
        MenuCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        MenuItem item = mapper.toEntity(dto);
        item.setCategory(category);

        return mapper.toDto(itemRepository.save(item));
    }

    public MenuItemDto update(Long id, MenuItemCreateDto dto) {
        return itemRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setDescription(dto.getDescription());
            existing.setPrice(dto.getPrice());
            existing.setImageUrl(dto.getImageUrl());
            existing.setAvailable(dto.isAvailable());

            MenuCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
            existing.setCategory(category);

            return mapper.toDto(itemRepository.save(existing));
        }).orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}