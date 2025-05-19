package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.TableAccessRequestDto;
import com.qrmenu.backend.entity.RestaurantTable;
import com.qrmenu.backend.entity.TableAccessLog;
import com.qrmenu.backend.repository.RestaurantTableRepository;
import com.qrmenu.backend.repository.TableAccessLogRepository;
import org.springframework.stereotype.Service;

@Service
public class TableAccessLogService {

    private final TableAccessLogRepository logRepository;
    private final RestaurantTableRepository tableRepository;

    public TableAccessLogService(TableAccessLogRepository logRepository,
                                 RestaurantTableRepository tableRepository) {
        this.logRepository = logRepository;
        this.tableRepository = tableRepository;
    }

    public void logAccess(TableAccessRequestDto dto) {
        RestaurantTable table = tableRepository.findByCode(dto.getTableCode())
                .orElseThrow(() -> new RuntimeException("Masa kodu bulunamadı"));

        TableAccessLog log = TableAccessLog.builder()
                .table(table)
                .build();

        logRepository.save(log);
    }
}