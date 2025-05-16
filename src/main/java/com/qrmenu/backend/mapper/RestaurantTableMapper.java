package com.qrmenu.backend.mapper;

import com.qrmenu.backend.dto.RestaurantTableCreateDto;
import com.qrmenu.backend.dto.RestaurantTableDto;
import com.qrmenu.backend.entity.RestaurantTable;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestaurantTableMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    RestaurantTableDto toDto(RestaurantTable table);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    RestaurantTable toEntity(RestaurantTableCreateDto dto);
}