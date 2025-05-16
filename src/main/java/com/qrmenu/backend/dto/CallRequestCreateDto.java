package com.qrmenu.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallRequestCreateDto {

    private String note;

    @NotNull(message = "Masa ID'si belirtilmelidir")
    private Long tableId;
}