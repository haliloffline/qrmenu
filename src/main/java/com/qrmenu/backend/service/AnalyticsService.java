package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.TableAccessStatsDto;
import com.qrmenu.backend.dto.TableUsageStatsDto;
import com.qrmenu.backend.repository.CallRequestRepository;
import com.qrmenu.backend.repository.TableAccessLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}