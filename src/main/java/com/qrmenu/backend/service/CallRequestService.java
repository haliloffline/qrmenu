package com.qrmenu.backend.service;

import com.qrmenu.backend.dto.CallRequestCreateDto;
import com.qrmenu.backend.dto.CallRequestDto;
import com.qrmenu.backend.entity.CallRequest;
import com.qrmenu.backend.entity.CallStatus;
import com.qrmenu.backend.entity.RestaurantTable;
import com.qrmenu.backend.mapper.CallRequestMapper;
import com.qrmenu.backend.repository.CallRequestRepository;
import com.qrmenu.backend.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CallRequestService {

    private final CallRequestRepository callRepository;
    private final RestaurantTableRepository tableRepository;
    private final CallRequestMapper mapper;

    public CallRequestService(CallRequestRepository callRepository,
                              RestaurantTableRepository tableRepository,
                              CallRequestMapper mapper) {
        this.callRepository = callRepository;
        this.tableRepository = tableRepository;
        this.mapper = mapper;
    }

    public List<CallRequestDto> getPendingCalls() {
        return callRepository.findByStatusOrderByCreatedAtAsc(CallStatus.PENDING).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CallRequestDto> getById(Long id) {
        return callRepository.findById(id).map(mapper::toDto);
    }

    public CallRequestDto create(CallRequestCreateDto dto) {
        RestaurantTable table = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));

        CallRequest request = mapper.toEntity(dto);
        request.setTable(table);

        return mapper.toDto(callRepository.save(request));
    }

    public CallRequestDto markAsResolved(Long id) {
        return callRepository.findById(id).map(existing -> {
            existing.setStatus(CallStatus.RESOLVED);
            return mapper.toDto(callRepository.save(existing));
        }).orElseThrow(() -> new RuntimeException("Çağrı bulunamadı"));
    }

    public void delete(Long id) {
        callRepository.deleteById(id);
    }
}