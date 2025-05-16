package com.qrmenu.backend.controller;

import com.qrmenu.backend.dto.RestaurantTableCreateDto;
import com.qrmenu.backend.dto.RestaurantTableDto;
import com.qrmenu.backend.service.RestaurantTableService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    public RestaurantTableController(RestaurantTableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableDto>> getAll() {
        return ResponseEntity.ok(tableService.getAll());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<RestaurantTableDto>> getByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(tableService.getByRestaurantId(restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableDto> getById(@PathVariable Long id) {
        return tableService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RestaurantTableDto> create(@Valid @RequestBody RestaurantTableCreateDto dto) {
        return ResponseEntity.ok(tableService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody RestaurantTableCreateDto dto) {
        return ResponseEntity.ok(tableService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}