package com.qrmenu.backend.controller;

import com.qrmenu.backend.dto.CallRequestCreateDto;
import com.qrmenu.backend.dto.CallRequestDto;
import com.qrmenu.backend.service.CallRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/call-requests")
public class CallRequestController {

    private final CallRequestService callService;

    public CallRequestController(CallRequestService callService) {
        this.callService = callService;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<CallRequestDto>> getPending() {
        return ResponseEntity.ok(callService.getPendingCalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallRequestDto> getById(@PathVariable Long id) {
        return callService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CallRequestDto> create(@Valid @RequestBody CallRequestCreateDto dto) {
        return ResponseEntity.ok(callService.create(dto));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<CallRequestDto> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(callService.markAsResolved(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        callService.delete(id);
        return ResponseEntity.noContent().build();
    }
}