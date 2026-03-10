package com.v1.SmartCapital.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PortfolioUploadDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String delIdLast4Digits;
    private String lenderId;
    private String borrowerName;

    private LocalDate dateOfBirth;
    private String gender;

    private String emailId;
    private String emailId2;

    @Column(length = 20)
    private String phoneNumberAtDisbursal;
    @Column(length = 20)
    private String currentPhoneNumber;
    @Column(length = 20)
    private String officePhoneNumber;
    @Column(length = 20)
    private String otherNumber;

    @Column(length = 1000)
    private String kycAddress;

    @Column(length = 10)
    private String zipCode;

    @Column(length = 300)
    private String currentPostalAddress;

    private String zipCode2;

    private String kycDocs;
    @Column(length = 15)
    private String panCardNumber;
    @Column(length = 20)
    private String aadhaarNumber;

    private String drivingLicenceNumber;
    private LocalDate drivingLicenceIssueDate;
    private LocalDate drivingLicenceExpiryDate;

    private String voterIdNumber;

    private String passportNumber;
    private LocalDate passportIssueDate;
    private LocalDate passportExpiryDate;

    private String rationCardNumber;
    private String otherOvd;

    private LocalDate loanSanctionDate;
    private LocalDate loanDisbursalDate;

    private BigDecimal amountDisbursedToLoanAccount;
    private BigDecimal initialLoanAmount;

    private Integer loanTenure;
    private BigDecimal loanInterest;

    @Column(length = 300)
    private String bankAccountDetails;

    private String loanDocument;
    private String loanPurchasedIntimationToBorrower;

    private BigDecimal principalOutstandingOnTakeoverDate;
    private BigDecimal totalOutstandingOnTakeoverDate;

    private LocalDate dateOfLastPaymentOnTakeoverDate;

    private BigDecimal interestAmountOnTakeoverDate;
    private BigDecimal penalInterestAmountOnTakeoverDate;

    private BigDecimal emiAmount;

    private String paymentFrequency;

    private BigDecimal amountReceivedByOriginalLenderAtPortfolioPurchase;

    private Integer dpdOnTakeoverDate;

    private String purposeOfLoan;
    private String occupationTypeOfBorrower;

    private String incomeFrequency;

    private BigDecimal familyIncome;

    private String restructured;

    @Column(length = 1000)
    private String originalLoanDetailsIfRestructured;

    private String borrowerKycNumber;
    private String anyLegalAction;
    private Integer ownershipIndicator;

    private BigDecimal principalOutstanding;
    private BigDecimal amountOutstandingAsOnDate;

    private LocalDate dateOfLastPayment;

    private String utrNumberForPayment;

    private BigDecimal interestValue;
    private BigDecimal penalInterestValue;

    private Integer numberOfPaymentsDone;

    private Integer dpd;

    private BigDecimal amountRecoveredByDel;

    private String accountStatus;

    private LocalDate accountClosedOrSettledDate;
    private LocalDate nocSentDate;

    private BigDecimal bureauScore;
    private Integer internalScore;

    private String bankAccountNumber;
    private String bankIfsc;

    @Column(length = 300)
    private String branchAddress;

    private Integer numberOfEmiPaid;
    private Integer paymentsDoneTillPortfolioPurchase;

    private String modeOfPayment;
    private String neftOrUpiDetails;

    private LocalDate dateOfEachPayment;

    private BigDecimal amountOfEachPayment;

    private String statementOfAccount;

    private String loanDocumentSentMail;

    private String bureauReportingByOriginalLender;

    @Column(length = 2000)
    private String processNoteLoanVerification;

    private String loanTakenFromMerchant;

    private BigDecimal excessPayment;

    private String electricityBill;
    private String rentAgreement;
    private String insuranceReceipt;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private PortfolioDetails portfolioDetails;
}
