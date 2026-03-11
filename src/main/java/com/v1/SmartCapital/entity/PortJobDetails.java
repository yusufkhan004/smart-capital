package com.v1.SmartCapital.entity;

import com.v1.SmartCapital.audit.Auditable;
import com.v1.SmartCapital.enums.JobStatus;
import com.v1.SmartCapital.enums.PortUploadType;
import com.v1.SmartCapital.enums.PortfolioType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class PortJobDetails extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long portfolioDetailsId;
    Long paymentDetailsId;
    String portfolioNumber;
    String portfolioName;
    @Enumerated(EnumType.STRING)
    JobStatus jobStatus = JobStatus.INPROGRESS;
    Long noOfAccounts = 0L;
    Long noOfSuccessAccounts = 0L;
    Long noOfErrorAccounts = 0L;
    Long noOfDuplicateAccounts = 0L;
    Long noOfSuspenseRecords = 0L;
    String fileName;
    BigDecimal version;
    Long templateId;
    @Enumerated(EnumType.STRING)
    PortUploadType uploadType;
    @Enumerated(EnumType.STRING)
    PortfolioType portfolioType;

    public PortJobDetails(Long portfolioDetailsId, String portfolioNumber, String portfolioName, JobStatus jobStatus, Long noOfAccounts, String fileName, BigDecimal version, Long templateId, PortUploadType uploadType, PortfolioType portfolioType) {
        this.portfolioDetailsId = portfolioDetailsId;
        this.portfolioNumber = portfolioNumber;
        this.portfolioName = portfolioName;
        this.jobStatus = jobStatus;
        this.noOfAccounts = noOfAccounts;
        this.fileName = fileName;
        this.version = version;
        this.templateId = templateId;
        this.uploadType = uploadType;
        this.portfolioType = portfolioType;
    }
    public PortJobDetails(Long paymentDetailsId, JobStatus jobStatus, Long noOfAccounts, String fileName, Long templateId) {
        this.paymentDetailsId = paymentDetailsId;
        this.jobStatus = jobStatus;
        this.noOfAccounts = noOfAccounts;
        this.fileName = fileName;
        this.templateId = templateId;
    }
    public PortJobDetails(Long portfolioDetailsId, String portfolioName, JobStatus jobStatus, Long noOfAccounts, String fileName, Long templateId, PortUploadType uploadType) {
        this.portfolioDetailsId = portfolioDetailsId;
        this.portfolioName = portfolioName;
        this.jobStatus = jobStatus;
        this.noOfAccounts = noOfAccounts;
        this.fileName = fileName;
        this.templateId = templateId;
        this.uploadType = uploadType;
    }
}