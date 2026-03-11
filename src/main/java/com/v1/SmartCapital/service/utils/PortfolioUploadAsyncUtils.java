package com.v1.SmartCapital.service.utils;

import com.v1.SmartCapital.constants.ValidationConstants;
import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.entity.PortfolioDetails;
import com.v1.SmartCapital.exception.NotFoundException;
import com.v1.SmartCapital.repository.PortfolioUploadRepository;
import com.v1.SmartCapital.response.TokenResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

@Component
public class PortfolioUploadAsyncUtils {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioUploadAsyncUtils.class);

    @Autowired
    PortfolioUploadRepository portfolioDetailsRepository;

    public void portfolioUploadAsyncNew(Long portfolioDetailsId, String delUniqueIdPrefix, Long portJobDetailsId, TokenResponse tokenResponse) {
        logger.info("Starting new portfolio upload processing for Portfolio ID: {}", portfolioDetailsId);
        try {
            PortfolioDetailsDTO portfolioDetailsDTO = getPortfolioDetails(portfolioDetailsId);
            File file = new File(portfolioDetailsDTO.getFilePath());
            FileInputStream fileInputStream = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //TODO continure processing the excel file and saving it to the Database
            logger.info("Successfully processed new portfolio ID: {}", portfolioDetailsId);
        } catch (Exception e) {
            logger.error("Failed to process new portfolio ID: {}", portfolioDetailsId, e);
        }
    }

    private PortfolioDetailsDTO getPortfolioDetails(Long portfolioDetailsId) {
        Optional<PortfolioDetails> optionalPortfolioDetails = portfolioDetailsRepository.findById(portfolioDetailsId);
        if (optionalPortfolioDetails.isEmpty())
            throw new NotFoundException("Portfolio Details Not Found");
        return optionalPortfolioDetails.get().getPortfolioDetailsDTO();
    }

    //TODO: use this method to get any value from cell of the excel sheet, and it will always return String at first
    private String getRowValue(Row row, String key) {

        Map<String, Long> columnMappings = new HashMap<>(); //TODO fetch all the columns of the excels in this map
        Long columnIndex = columnMappings.getOrDefault(key, null);
        Cell cell;

        if (columnIndex == null) {
            Map<String, Long> map = readHeaders(row.getSheet());
            columnIndex = map.getOrDefault(key, null);
        }

        if (columnIndex != null) {
            int columnIndexInt = Math.toIntExact(columnIndex);
            cell = row.getCell(columnIndexInt);

            if (cell == null || cell.getCellType() == CellType.BLANK) {
                return null;
            }
        } else {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            double numericValue = cell.getNumericCellValue();

            if (key.equals("aadhaarNumber"))
                return (numericValue == (long) numericValue) ? String.format("%d", (long) numericValue)
                        : String.valueOf(numericValue);

            String dataFormatString = cell.getCellStyle().getDataFormatString();
            if ((dataFormatString.startsWith(ValidationConstants.PRE_GREGORIAN_DATE_FORMAT) && dataFormatString.endsWith(ValidationConstants.POST_GREGORIAN_DATE_FORMAT))
                    || DateUtil.isCellDateFormatted(cell))
                return new SimpleDateFormat(ValidationConstants.ISO_DATE_FORMAT).format(DateUtil.getJavaDate((long) numericValue));

            String stringValue = String.valueOf(numericValue);
            if (stringValue.matches("^\\d+(\\.0)?$"))
                return stringValue.substring(0, stringValue.indexOf('.'));
        }
        return StringUtils.trimWhitespace(String.valueOf(cell));
    }

    public static Map<String, Long> readHeaders(Sheet sheet) {
        Row headerRow = sheet.getRow(0);
        return IntStream.range(headerRow.getFirstCellNum(), headerRow.getPhysicalNumberOfCells())
                .mapToObj(colIndex -> {Cell cell = headerRow.getCell(colIndex);
                    return (cell != null) ? Map.entry(cell.getStringCellValue(),(long) colIndex) : null;
                }).filter(Objects::nonNull).collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }
}