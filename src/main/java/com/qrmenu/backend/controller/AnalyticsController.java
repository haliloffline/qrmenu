package com.qrmenu.backend.controller;

import com.qrmenu.backend.dto.DateRangeDto;
import com.qrmenu.backend.dto.TableAccessStatsDto;
import com.qrmenu.backend.dto.TableUsageStatsDto;
import com.qrmenu.backend.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/table-usage")
    public ResponseEntity<List<TableUsageStatsDto>> getTableUsageStats() {
        return ResponseEntity.ok(analyticsService.getMostUsedTables());
    }

    @GetMapping("/table-access")
    public ResponseEntity<List<TableAccessStatsDto>> getTableAccessStats() {
        return ResponseEntity.ok(analyticsService.getMostAccessedTables());
    }

    @PostMapping("/table-access/date-range")
    public ResponseEntity<List<TableAccessStatsDto>> getAccessStatsByRange(
            @RequestBody DateRangeDto dto
    ) {
        return ResponseEntity.ok(analyticsService.getAccessStatsByDateRange(dto));
    }

    @PostMapping("/table-usage/date-range")
    public ResponseEntity<List<TableUsageStatsDto>> getCallStatsByDateRange(
            @RequestBody DateRangeDto dto
    ) {
        return ResponseEntity.ok(analyticsService.getCallStatsByDateRange(dto));
    }


}