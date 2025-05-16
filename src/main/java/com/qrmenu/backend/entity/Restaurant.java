package com.qrmenu.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(name = "language_supported", columnDefinition = "TEXT")
    private String languageSupportedJson; // {"tr": true, "en": true} gibi json formatında saklanacak
}

