package com.qrmenu.backend.repository;

import com.qrmenu.backend.entity.CallRequest;
import com.qrmenu.backend.entity.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallRequestRepository extends JpaRepository<CallRequest, Long> {
    List<CallRequest> findByStatusOrderByCreatedAtAsc(CallStatus status);
}