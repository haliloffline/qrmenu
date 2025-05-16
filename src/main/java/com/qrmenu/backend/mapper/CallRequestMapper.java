package com.qrmenu.backend.mapper;

import com.qrmenu.backend.dto.CallRequestCreateDto;
import com.qrmenu.backend.dto.CallRequestDto;
import com.qrmenu.backend.entity.CallRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CallRequestMapper {

    @Mapping(source = "table.id", target = "tableId")
    @Mapping(source = "table.name", target = "tableName")
    CallRequestDto toDto(CallRequest entity);

    @Mapping(source = "tableId", target = "table.id")
    CallRequest toEntity(CallRequestCreateDto dto);
}