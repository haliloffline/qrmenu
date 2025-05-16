package com.qrmenu.backend.controller;

import com.qrmenu.backend.dto.MenuItemCreateDto;
import com.qrmenu.backend.dto.MenuItemDto;
import com.qrmenu.backend.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService itemService;

    public MenuItemController(MenuItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<MenuItemDto>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(itemService.getByCategoryId(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getById(@PathVariable Long id) {
        return itemService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuItemDto> create(@Valid @RequestBody MenuItemCreateDto dto) {
        return ResponseEntity.ok(itemService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDto> update(@PathVariable Long id,
                                              @Valid @RequestBody MenuItemCreateDto dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}