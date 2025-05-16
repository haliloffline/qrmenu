package com.qrmenu.backend.mapper;

import com.qrmenu.backend.dto.MenuCategoryCreateDto;
import com.qrmenu.backend.dto.MenuCategoryDto;
import com.qrmenu.backend.entity.MenuCategory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuCategoryDto toDto(MenuCategory category);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    MenuCategory toEntity(MenuCategoryCreateDto dto);
}