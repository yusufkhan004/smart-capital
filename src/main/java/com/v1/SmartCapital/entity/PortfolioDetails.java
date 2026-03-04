package com.v1.SmartCapital.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "portfolio_details")
public class PortfolioDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String portfolioName;

    @Column(nullable = false)
    String portfolioNo;

    @Column(nullable = false, precision = 3, scale = 1)
    BigDecimal versionNo;

    @Column(nullable = false)
    LocalDateTime cutOffDate;

    @Column(nullable = false)
    LocalDateTime executionDate;

    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(nullable = false)
    LocalDateTime updatedAt;
}
