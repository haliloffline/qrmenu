package com.qrmenu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantCreateDto {

    @NotBlank(message = "İsim boş olamaz")
    @Size(min = 2, max = 100, message = "İsim 2 ile 100 karakter arasında olmalı")
    private String name;

    @NotBlank(message = "Adres boş olamaz")
    @Size(min = 5, max = 200, message = "Adres 5 ile 200 karakter arasında olmalı")
    private String address;

    @NotBlank(message = "Dil bilgisi boş olamaz")
    private String languageSupportedJson;
}