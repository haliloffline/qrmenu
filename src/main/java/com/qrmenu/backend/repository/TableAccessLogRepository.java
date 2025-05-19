package com.qrmenu.backend.repository;

import com.qrmenu.backend.dto.TableAccessStatsDto;
import com.qrmenu.backend.entity.TableAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableAccessLogRepository extends JpaRepository<TableAccessLog, Long> {

    @Query("SELECT new com.qrmenu.backend.dto.TableAccessStatsDto(t.id, t.name, COUNT(l)) " +
            "FROM TableAccessLog l JOIN l.table t " +
            "GROUP BY t.id, t.name " +
            "ORDER BY COUNT(l) DESC")
    List<TableAccessStatsDto> getAccessStats();
}