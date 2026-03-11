package com.v1.SmartCapital.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PortfolioDetailsDTO {

    Long id;
    String portfolioName;
    Long portfolioNo;
    BigDecimal versionNo;
    String filePath;
    LocalDate cutOffDate;
    LocalDate executionDate;
    String fileName;
    LocalDateTime creationDate;
    LocalDateTime lastModifiedDate;
    String createdBy;
    String lastModifiedBy;

}
