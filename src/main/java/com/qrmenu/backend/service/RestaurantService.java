package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.RestaurantCreateDto;
import com.qrmenu.backend.dto.RestaurantDto;
import com.qrmenu.backend.entity.Restaurant;
import com.qrmenu.backend.mapper.RestaurantMapper;
import com.qrmenu.backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<RestaurantDto> getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::toDto);
    }

    public RestaurantDto createRestaurant(RestaurantCreateDto createDto) {
        Restaurant restaurant = restaurantMapper.toEntity(createDto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(saved);
    }

    public RestaurantDto updateRestaurant(Long id, RestaurantCreateDto updatedDto) {
        return restaurantRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedDto.getName());
                    existing.setAddress(updatedDto.getAddress());
                    existing.setLanguageSupportedJson(updatedDto.getLanguageSupportedJson());
                    return restaurantMapper.toDto(restaurantRepository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}