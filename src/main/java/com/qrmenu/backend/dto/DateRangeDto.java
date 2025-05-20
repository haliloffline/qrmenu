package com.qrmenu.backend.dto;

import com.qrmenu.backend.enums.DateRangeType;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateRangeDto {

    private DateRangeType rangeType;

    // CUSTOM seçildiğinde kullanılacak
    private LocalDate startDate;
    private LocalDate endDate;

    // RESTORAN bazlı analiz için eklendi
    private Long restaurantId;
}