package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.RestaurantTableCreateDto;
import com.qrmenu.backend.dto.RestaurantTableDto;
import com.qrmenu.backend.entity.Restaurant;
import com.qrmenu.backend.entity.RestaurantTable;
import com.qrmenu.backend.mapper.RestaurantTableMapper;
import com.qrmenu.backend.repository.RestaurantRepository;
import com.qrmenu.backend.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantTableMapper tableMapper;

    public RestaurantTableService(RestaurantTableRepository tableRepository,
                                  RestaurantRepository restaurantRepository,
                                  RestaurantTableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
        this.tableMapper = tableMapper;
    }

    public List<RestaurantTableDto> getAll() {
        return tableRepository.findAll().stream()
                .map(tableMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<RestaurantTableDto> getByRestaurantId(Long restaurantId) {
        return tableRepository.findByRestaurantId(restaurantId).stream()
                .map(tableMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<RestaurantTableDto> getById(Long id) {
        return tableRepository.findById(id)
                .map(tableMapper::toDto);
    }

    public RestaurantTableDto create(RestaurantTableCreateDto dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restoran bulunamadı"));

        RestaurantTable table = tableMapper.toEntity(dto);
        table.setRestaurant(restaurant); // ilişkiyi kur

        return tableMapper.toDto(tableRepository.save(table));
    }

    public RestaurantTableDto update(Long id, RestaurantTableCreateDto dto) {
        return tableRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setCode(dto.getCode());

            Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                    .orElseThrow(() -> new RuntimeException("Restoran bulunamadı"));
            existing.setRestaurant(restaurant);

            return tableMapper.toDto(tableRepository.save(existing));
        }).orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}