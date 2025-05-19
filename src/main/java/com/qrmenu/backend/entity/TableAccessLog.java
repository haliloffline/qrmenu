package com.qrmenu.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_access_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableAccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @Column(name = "accessed_at", nullable = false)
    private LocalDateTime accessedAt;

    @PrePersist
    public void onCreate() {
        this.accessedAt = LocalDateTime.now();
    }
}