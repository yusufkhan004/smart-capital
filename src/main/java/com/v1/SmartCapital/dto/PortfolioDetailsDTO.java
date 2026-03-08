package com.v1.SmartCapital.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PortfolioDetailsDTO {

    String portfolioName;
    Long portfolioNo;
    BigDecimal versionNo;
    LocalDate cutOffDate;
    LocalDate executionDate;

}
