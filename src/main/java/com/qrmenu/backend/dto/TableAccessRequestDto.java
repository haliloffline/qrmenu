package com.qrmenu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableAccessRequestDto {

    @NotBlank(message = "Masa kodu boş olamaz")
    private String tableCode;
}