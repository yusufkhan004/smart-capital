package com.v1.SmartCapital.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ExcelCellUtils {
    private final DataFormatter formatter = new DataFormatter();

    public String getString(Cell cell) {

        if (cell == null)
            return null;

        return formatter.formatCellValue(cell).trim();
    }

    public LocalDate getDate(Cell cell) {

        if (cell == null)
            return null;

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }

        return null;
    }

    public BigDecimal getDecimal(Cell cell) {

        if (cell == null)
            return null;

        // safest path for numeric cells
        if (cell.getCellType() == CellType.NUMERIC) {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        }

        String value = formatter.formatCellValue(cell);

        if (value == null || value.isBlank())
            return null;

        value = value
                .replace(",", "")
                .replace("\"", "")
                .replaceAll("[^0-9.\\-]", "")
                .trim();

        if (value.isEmpty())
            return null;

        return new BigDecimal(value);
    }

    public Integer getInteger(Cell cell) {

        if (cell == null)
            return null;

        if (cell.getCellType() == CellType.NUMERIC) {

            double value = cell.getNumericCellValue();

            if (value % 1 != 0) {
                throw new RuntimeException("Invalid integer value in Excel: " + value);
            }

            return (int) value;
        }

        if (cell.getCellType() == CellType.STRING) {
            return Integer.parseInt(cell.getStringCellValue());
        }

        return null;
    }
}
