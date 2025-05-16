package com.qrmenu.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private CallStatus status;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = CallStatus.PENDING;
    }
}