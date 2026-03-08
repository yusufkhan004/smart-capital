package com.v1.SmartCapital.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class PortfolioDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100)
    String portfolioName;

    @Column(nullable = false)
    Long portfolioNo;

    @Column(nullable = false, precision = 3, scale = 10)
    BigDecimal versionNo;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate cutOffDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate executionDate;

    String fileName;

    String filePath;

    String fileType;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "DATETIME(0)")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "DATETIME(0)")
    LocalDateTime updatedAt;
}
