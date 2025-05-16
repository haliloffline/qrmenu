package com.qrmenu.backend.mapper;

import com.qrmenu.backend.dto.MenuItemCreateDto;
import com.qrmenu.backend.dto.MenuItemDto;
import com.qrmenu.backend.entity.MenuItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(source = "category.id", target = "categoryId")
    MenuItemDto toDto(MenuItem entity);

    @Mapping(source = "categoryId", target = "category.id")
    MenuItem toEntity(MenuItemCreateDto dto);
}