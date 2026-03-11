package com.v1.SmartCapital.entity;

import com.v1.SmartCapital.audit.Auditable;
import com.v1.SmartCapital.dto.MasterPortfolioDTO;
import com.v1.SmartCapital.enums.PortfolioType;
import com.v1.SmartCapital.util.CommonUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"portfolio_number", "portfolio_name"}))
public class MasterPortfolio extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "portfolio_name")
    String portfolioName;
    String lenderCode; //AA
    String intermediaryCode; //BB
    @Column(name = "portfolio_number")
    String portfolioNumber; //CC
    Boolean isActive = Boolean.TRUE;
    Date cutOffDate;
    Date executionDate;
    String portIntermediaryNo;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    PortfolioType portfolioType;

    public MasterPortfolio(String portfolioName, String lenderCode, String intermediaryCode, String portfolioNumber, Date cutOffDate, Date executionDate, String portIntermediaryNo) {
        this.portfolioName = portfolioName;
        this.lenderCode = lenderCode;
        this.intermediaryCode = intermediaryCode;
        this.portfolioNumber = portfolioNumber;
        this.cutOffDate = cutOffDate;
        this.executionDate = executionDate;
        this.portIntermediaryNo = portIntermediaryNo;
    }

    public MasterPortfolioDTO getMasterPortfolioDTO(){
        return new MasterPortfolioDTO(id, portfolioName, lenderCode, intermediaryCode, portfolioNumber, cutOffDate, executionDate, isActive, CommonUtils.getUsername(createdBy), creationDate,
                CommonUtils.getUsername(lastModifiedBy), lastModifiedDate, portIntermediaryNo, amount, portfolioType);
    }
}