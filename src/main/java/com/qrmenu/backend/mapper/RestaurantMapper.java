package com.qrmenu.backend.mapper;

import com.qrmenu.backend.dto.RestaurantCreateDto;
import com.qrmenu.backend.dto.RestaurantDto;
import com.qrmenu.backend.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant toEntity(RestaurantCreateDto dto);

    RestaurantDto toDto(Restaurant restaurant);
}