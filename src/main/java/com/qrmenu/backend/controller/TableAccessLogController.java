package com.qrmenu.backend.controller;

import com.qrmenu.backend.dto.TableAccessRequestDto;
import com.qrmenu.backend.service.TableAccessLogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/table-access-log")
public class TableAccessLogController {

    private final TableAccessLogService accessLogService;

    public TableAccessLogController(TableAccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @PostMapping
    public ResponseEntity<Void> logAccess(@Valid @RequestBody TableAccessRequestDto dto) {
        accessLogService.logAccess(dto);
        return ResponseEntity.ok().build();
    }
}