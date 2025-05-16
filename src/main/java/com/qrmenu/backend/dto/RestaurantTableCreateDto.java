package com.qrmenu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableCreateDto {

    @NotBlank(message = "Masa adı boş olamaz")
    private String name;

    @NotBlank(message = "Kod boş olamaz")
    private String code;

    @NotNull(message = "Restoran ID belirtilmelidir")
    private Long restaurantId;
}