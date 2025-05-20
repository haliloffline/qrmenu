package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.DateRangeDto;
import com.qrmenu.backend.dto.TableAccessStatsDto;
import com.qrmenu.backend.dto.TableUsageStatsDto;
import com.qrmenu.backend.repository.CallRequestRepository;
import com.qrmenu.backend.repository.TableAccessLogRepository;
import org.springframework.stereotype.Service;
import com.qrmenu.backend.util.DateRangeUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    private final TableAccessLogRepository tableAccessLogRepository;

    private final CallRequestRepository callRequestRepository;

    public AnalyticsService(TableAccessLogRepository tableAccessLogRepository, CallRequestRepository callRequestRepository) {
        this.tableAccessLogRepository = tableAccessLogRepository;
        this.callRequestRepository = callRequestRepository;
    }

    public List<TableUsageStatsDto> getMostUsedTables() {
        return callRequestRepository.getTableUsageStats();
    }

    public List<TableAccessStatsDto> getMostAccessedTables() {
        return tableAccessLogRepository.getAccessStats();
    }

    public List<TableAccessStatsDto> getAccessStatsByDateRange(DateRangeDto dto) {
        Map<String, LocalDate> range = DateRangeUtil.resolve(dto);

        // Tarih aralığı hazırlanıyor
        LocalDateTime start = null;
        LocalDateTime end = null;

        if (range != null) {
            start = range.get("start").atStartOfDay();
            end = range.get("end").atTime(23, 59, 59);
        }

        if (dto.getRestaurantId() != null) {
            // RESTORAN filtreli analiz
            if (range == null) {
                throw new IllegalArgumentException("Restoran bazlı analiz için tarih aralığı gerekli.");
            }
            return tableAccessLogRepository.getAccessStatsBetweenByRestaurant(dto.getRestaurantId(), start, end);
        } else {
            // RESTORAN seçilmemiş → sistem geneli analiz
            if (range == null) {
                return tableAccessLogRepository.getAccessStats(); // tüm veriler
            } else {
                return tableAccessLogRepository.getAccessStatsBetween(start, end);
            }
        }
    }
}