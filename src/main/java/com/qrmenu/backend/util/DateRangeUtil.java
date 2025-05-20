package com.qrmenu.backend.util;

import com.qrmenu.backend.dto.DateRangeDto;
import com.qrmenu.backend.enums.DateRangeType;

import java.time.LocalDate;
import java.util.Map;

public class DateRangeUtil {

    public static Map<String, LocalDate> resolve(DateRangeDto dto) {
        LocalDate now = LocalDate.now();
        LocalDate start;
        LocalDate end;

        switch (dto.getRangeType()) {
            case DAILY -> {
                start = now;
                end = now;
            }
            case WEEKLY -> {
                start = now.minusDays(6); // son 7 gün
                end = now;
            }
            case MONTHLY -> {
                start = now.withDayOfMonth(1);
                end = now;
            }
            case YEARLY -> {
                start = now.withDayOfYear(1);
                end = now;
            }
            case CUSTOM -> {
                start = dto.getStartDate();
                end = dto.getEndDate();
                if (start == null || end == null || end.isBefore(start)) {
                    throw new IllegalArgumentException("Geçersiz özel tarih aralığı");
                }
            }
            case ALL -> {
                return null; // sınırsız, tarih filtresi uygulanmaz
            }
            default -> throw new IllegalArgumentException("Geçersiz tarih tipi");
        }

        return Map.of(
                "start", start,
                "end", end
        );
    }
}