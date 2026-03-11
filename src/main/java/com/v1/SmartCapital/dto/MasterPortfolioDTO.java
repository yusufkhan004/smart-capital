package com.v1.SmartCapital.dto;

import com.v1.SmartCapital.enums.PortfolioType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MasterPortfolioDTO {
    Long id;
    String portfolioName;
    String lenderCode; //AA
    String intermediaryCode; //BB
    String portfolioNumber; //CC
    Date cutOffDate;
    Date executionDate;
    Boolean isActive;
    String createdBy;
    LocalDateTime creationDate;
    String lastModifiedBy;
    LocalDateTime lastModifiedDate;
    String portIntermediaryNo;
    BigDecimal amount;
    PortfolioType portfolioType;

    public MasterPortfolioDTO(Long id, String portfolioName, String lenderCode, String intermediaryCode, String portfolioNumber, Date cutOffDate, Date executionDate,
                              Boolean isActive, String createdBy, LocalDateTime creationDate, String lastModifiedBy, LocalDateTime lastModifiedDate, String portIntermediaryNo, BigDecimal amount,
                              PortfolioType portfolioType) {
        this.id = id;
        this.portfolioName = portfolioName;
        this.lenderCode = lenderCode;
        this.intermediaryCode = intermediaryCode;
        this.portfolioNumber = portfolioNumber;
        this.cutOffDate = cutOffDate;
        this.executionDate = executionDate;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.portIntermediaryNo = portIntermediaryNo;
        this.amount = amount;
        this.portfolioType = portfolioType;
    }
}
