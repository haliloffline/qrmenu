package com.qrmenu.backend.repository;

import com.qrmenu.backend.dto.TableUsageStatsDto;
import com.qrmenu.backend.entity.CallRequest;
import com.qrmenu.backend.entity.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallRequestRepository extends JpaRepository<CallRequest, Long> {
    List<CallRequest> findByStatusOrderByCreatedAtAsc(CallStatus status);

    @Query("SELECT new com.qrmenu.backend.dto.TableUsageStatsDto(t.id, t.name, COUNT(c)) " +
            "FROM CallRequest c JOIN c.table t " +
            "GROUP BY t.id, t.name " +
            "ORDER BY COUNT(c) DESC")
    List<TableUsageStatsDto> getTableUsageStats();
}