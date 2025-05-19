package com.qrmenu.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableUsageStatsDto {
    private Long tableId;
    private String tableName;
    private Long callCount;
}