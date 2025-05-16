package com.qrmenu.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemCreateDto {

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir")
    private String description;

    @NotNull(message = "Fiyat belirtilmelidir")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fiyat sıfırdan büyük olmalıdır")
    private BigDecimal price;

    private String imageUrl;

    private boolean isAvailable = true;

    @NotNull(message = "Kategori ID belirtilmelidir")
    private Long categoryId;
}