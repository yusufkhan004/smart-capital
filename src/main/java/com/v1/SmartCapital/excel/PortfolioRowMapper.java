package com.v1.SmartCapital.excel;

import com.v1.SmartCapital.entity.PortfolioUploadDetails;
import com.v1.SmartCapital.util.ExcelCellUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioRowMapper {

    private final ExcelCellUtils excelCellUtils;

    public PortfolioUploadDetails map(Row row) {

        PortfolioUploadDetails entity = new PortfolioUploadDetails();

        entity.setDelIdLast4Digits(excelCellUtils.getString(row.getCell(0)));
        entity.setLenderId(excelCellUtils.getString(row.getCell(1)));
        entity.setBorrowerName(excelCellUtils.getString(row.getCell(2)));
        entity.setDateOfBirth(excelCellUtils.getDate(row.getCell(3)));
        entity.setGender(excelCellUtils.getString(row.getCell(4)));
        entity.setEmailId(excelCellUtils.getString(row.getCell(5)));
        entity.setEmailId2(excelCellUtils.getString(row.getCell(6)));

        entity.setPhoneNumberAtDisbursal(excelCellUtils.getString(row.getCell(7)));
        entity.setCurrentPhoneNumber(excelCellUtils.getString(row.getCell(8)));
        entity.setOfficePhoneNumber(excelCellUtils.getString(row.getCell(9)));
        entity.setOtherNumber(excelCellUtils.getString(row.getCell(10)));

        entity.setKycAddress(excelCellUtils.getString(row.getCell(11)));
        entity.setZipCode(excelCellUtils.getString(row.getCell(12)));
        entity.setCurrentPostalAddress(excelCellUtils.getString(row.getCell(13)));
        entity.setZipCode2(excelCellUtils.getString(row.getCell(14)));

        entity.setKycDocs(excelCellUtils.getString(row.getCell(15)));
        entity.setPanCardNumber(excelCellUtils.getString(row.getCell(16)));
        entity.setAadhaarNumber(excelCellUtils.getString(row.getCell(17)));

        entity.setDrivingLicenceNumber(excelCellUtils.getString(row.getCell(18)));
        entity.setDrivingLicenceIssueDate(excelCellUtils.getDate(row.getCell(19)));
        entity.setDrivingLicenceExpiryDate(excelCellUtils.getDate(row.getCell(20)));

        entity.setVoterIdNumber(excelCellUtils.getString(row.getCell(21)));

        entity.setPassportNumber(excelCellUtils.getString(row.getCell(22)));
        entity.setPassportIssueDate(excelCellUtils.getDate(row.getCell(23)));
        entity.setPassportExpiryDate(excelCellUtils.getDate(row.getCell(24)));

        entity.setRationCardNumber(excelCellUtils.getString(row.getCell(25)));
        entity.setOtherOvd(excelCellUtils.getString(row.getCell(26)));

        entity.setLoanSanctionDate(excelCellUtils.getDate(row.getCell(27)));
        entity.setLoanDisbursalDate(excelCellUtils.getDate(row.getCell(28)));

        entity.setAmountDisbursedToLoanAccount(excelCellUtils.getDecimal(row.getCell(29)));
        entity.setInitialLoanAmount(excelCellUtils.getDecimal(row.getCell(30)));

        entity.setLoanTenure(excelCellUtils.getInteger(row.getCell(31)));
        entity.setLoanInterest(excelCellUtils.getDecimal(row.getCell(32)));

        entity.setBankAccountDetails(excelCellUtils.getString(row.getCell(33)));

        entity.setLoanDocument(excelCellUtils.getString(row.getCell(34)));
        entity.setLoanPurchasedIntimationToBorrower(excelCellUtils.getString(row.getCell(35)));

        entity.setPrincipalOutstandingOnTakeoverDate(excelCellUtils.getDecimal(row.getCell(36)));
        entity.setTotalOutstandingOnTakeoverDate(excelCellUtils.getDecimal(row.getCell(37)));

        entity.setDateOfLastPaymentOnTakeoverDate(excelCellUtils.getDate(row.getCell(38)));

        entity.setInterestAmountOnTakeoverDate(excelCellUtils.getDecimal(row.getCell(39)));
        entity.setPenalInterestAmountOnTakeoverDate(excelCellUtils.getDecimal(row.getCell(40)));

        entity.setEmiAmount(excelCellUtils.getDecimal(row.getCell(41)));

        entity.setPaymentFrequency(excelCellUtils.getString(row.getCell(42)));

        entity.setAmountReceivedByOriginalLenderAtPortfolioPurchase(excelCellUtils.getDecimal(row.getCell(43)));

        entity.setDpdOnTakeoverDate(excelCellUtils.getInteger(row.getCell(44)));

        entity.setPurposeOfLoan(excelCellUtils.getString(row.getCell(45)));
        entity.setOccupationTypeOfBorrower(excelCellUtils.getString(row.getCell(46)));

        entity.setIncomeFrequency(excelCellUtils.getString(row.getCell(47)));

        entity.setFamilyIncome(excelCellUtils.getDecimal(row.getCell(48)));

        entity.setRestructured(excelCellUtils.getString(row.getCell(49)));

        entity.setOriginalLoanDetailsIfRestructured(excelCellUtils.getString(row.getCell(50)));

        entity.setBorrowerKycNumber(excelCellUtils.getString(row.getCell(51)));
        entity.setAnyLegalAction(excelCellUtils.getString(row.getCell(52)));
        entity.setOwnershipIndicator(excelCellUtils.getInteger(row.getCell(53)));

        entity.setPrincipalOutstanding(excelCellUtils.getDecimal(row.getCell(54)));
        entity.setAmountOutstandingAsOnDate(excelCellUtils.getDecimal(row.getCell(55)));

        entity.setDateOfLastPayment(excelCellUtils.getDate(row.getCell(56)));

        entity.setUtrNumberForPayment(excelCellUtils.getString(row.getCell(57)));

        entity.setInterestValue(excelCellUtils.getDecimal(row.getCell(58)));
        entity.setPenalInterestValue(excelCellUtils.getDecimal(row.getCell(59)));

        entity.setNumberOfPaymentsDone(excelCellUtils.getInteger(row.getCell(60)));

        entity.setDpd(excelCellUtils.getInteger(row.getCell(61)));

        entity.setAmountRecoveredByDel(excelCellUtils.getDecimal(row.getCell(62)));

        entity.setAccountStatus(excelCellUtils.getString(row.getCell(63)));

        entity.setAccountClosedOrSettledDate(excelCellUtils.getDate(row.getCell(64)));
        entity.setNocSentDate(excelCellUtils.getDate(row.getCell(65)));

        entity.setBureauScore(excelCellUtils.getDecimal(row.getCell(66)));
        entity.setInternalScore(excelCellUtils.getInteger(row.getCell(67)));

        entity.setBankAccountNumber(excelCellUtils.getString(row.getCell(68)));
        entity.setBankIfsc(excelCellUtils.getString(row.getCell(69)));

        entity.setBranchAddress(excelCellUtils.getString(row.getCell(70)));

        entity.setNumberOfEmiPaid(excelCellUtils.getInteger(row.getCell(71)));
        entity.setPaymentsDoneTillPortfolioPurchase(excelCellUtils.getInteger(row.getCell(72)));

        entity.setModeOfPayment(excelCellUtils.getString(row.getCell(73)));
        entity.setNeftOrUpiDetails(excelCellUtils.getString(row.getCell(74)));

        entity.setDateOfEachPayment(excelCellUtils.getDate(row.getCell(75)));

        entity.setAmountOfEachPayment(excelCellUtils.getDecimal(row.getCell(76)));

        entity.setStatementOfAccount(excelCellUtils.getString(row.getCell(77)));

        entity.setLoanDocumentSentMail(excelCellUtils.getString(row.getCell(78)));

        entity.setBureauReportingByOriginalLender(excelCellUtils.getString(row.getCell(79)));

        entity.setProcessNoteLoanVerification(excelCellUtils.getString(row.getCell(80)));

        entity.setLoanTakenFromMerchant(excelCellUtils.getString(row.getCell(81)));

        entity.setExcessPayment(excelCellUtils.getDecimal(row.getCell(82)));

        entity.setElectricityBill(excelCellUtils.getString(row.getCell(83)));
        entity.setRentAgreement(excelCellUtils.getString(row.getCell(84)));
        entity.setInsuranceReceipt(excelCellUtils.getString(row.getCell(85)));

        return entity;
    }
}
