package com.v1.SmartCapital.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.v1.SmartCapital.audit.Auditable;
import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class PortfolioDetails extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100)
    String portfolioName;

    @Column(nullable = false)
    Long portfolioNo;

    @Column(nullable = false, precision = 10, scale = 3)
    BigDecimal versionNo;

    String filePath;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate cutOffDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate executionDate;

    String fileName;

    //TODO Remove createdAt and updatedAt and checkout extended class named Auditable
    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "DATETIME(0)")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "DATETIME(0)")
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "portfolioDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PortfolioUploadDetails> uploadDetails = new ArrayList<>();

    public PortfolioDetailsDTO getPortfolioDetailsDTO() {
        return new PortfolioDetailsDTO(id, portfolioName, portfolioNo, versionNo, filePath, cutOffDate, executionDate, fileName, creationDate, lastModifiedDate,
                createdBy, lastModifiedBy);
    }
}
