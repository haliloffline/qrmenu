package com.qrmenu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCategoryDto {
    private Long id;
    private String name;
    private Long restaurantId;
    private Integer displayOrder;
}