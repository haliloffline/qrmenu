package com.qrmenu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableDto {
    private Long id;    //id artık dönecek
    private String name;
    private String code;
    private Long restaurantId; // Hangi restorana ait olduğu
}