package com.qrmenu.backend.repository;

import com.qrmenu.backend.dto.TableAccessStatsDto;
import com.qrmenu.backend.entity.TableAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TableAccessLogRepository extends JpaRepository<TableAccessLog, Long> {

    @Query("SELECT new com.qrmenu.backend.dto.TableAccessStatsDto(t.id, t.name, COUNT(l)) " +
            "FROM TableAccessLog l JOIN l.table t " +
            "GROUP BY t.id, t.name " +
            "ORDER BY COUNT(l) DESC")
    List<TableAccessStatsDto> getAccessStats();

    @Query("""
        SELECT new com.qrmenu.backend.dto.TableAccessStatsDto(t.id, t.name, COUNT(l))
        FROM TableAccessLog l
        JOIN l.table t
        WHERE l.accessedAt BETWEEN :start AND :end
        GROUP BY t.id, t.name
        ORDER BY COUNT(l) DESC
    """)
    List<TableAccessStatsDto> getAccessStatsBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
    SELECT new com.qrmenu.backend.dto.TableAccessStatsDto(t.id, t.name, COUNT(l))
    FROM TableAccessLog l
    JOIN l.table t
    WHERE t.restaurant.id = :restaurantId
      AND l.accessedAt BETWEEN :start AND :end
    GROUP BY t.id, t.name
    ORDER BY COUNT(l) DESC
""")
    List<TableAccessStatsDto> getAccessStatsBetweenByRestaurant(
            @Param("restaurantId") Long restaurantId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}