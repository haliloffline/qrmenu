package com.qrmenu.backend.dto;

import com.qrmenu.backend.entity.CallStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallRequestDto {
    private Long id;
    private Long tableId;
    private String tableName;
    private String note;
    private LocalDateTime createdAt;
    private CallStatus status;
}