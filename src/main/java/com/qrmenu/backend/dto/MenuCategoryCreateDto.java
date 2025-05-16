package com.qrmenu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCategoryCreateDto {

    @NotBlank(message = "Kategori adı boş olamaz")
    private String name;

    @NotNull(message = "Restoran ID belirtilmelidir")
    private Long restaurantId;

    private Integer displayOrder;
}