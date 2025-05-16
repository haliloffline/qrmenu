package com.qrmenu.backend.controller;

import com.qrmenu.backend.dto.MenuCategoryCreateDto;
import com.qrmenu.backend.dto.MenuCategoryDto;
import com.qrmenu.backend.service.MenuCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class MenuCategoryController {

    private final MenuCategoryService categoryService;

    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuCategoryDto>> getByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(categoryService.getByRestaurantId(restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuCategoryDto> getById(@PathVariable Long id) {
        return categoryService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuCategoryDto> create(@Valid @RequestBody MenuCategoryCreateDto dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuCategoryDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody MenuCategoryCreateDto dto) {
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}