package com.qrmenu.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableAccessStatsDto {
    private Long tableId;
    private String tableName;
    private Long accessCount;
}