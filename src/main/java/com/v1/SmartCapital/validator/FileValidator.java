package com.v1.SmartCapital.validator;

import com.v1.SmartCapital.exception.FileValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static com.v1.SmartCapital.constants.ErrorMsgConstants.UPLOADED_FILE_IS_MISSING_OR_EMPTY;
import static com.v1.SmartCapital.constants.GeneralMsgConstants.*;

@Component
public class FileValidator {
    public void validateExcelFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileValidationException(UPLOADED_FILE_IS_MISSING_OR_EMPTY);
        }

        String originalName = file.getOriginalFilename();
        if (originalName == null) {
            throw new FileValidationException(FILE_NAME_IS_INVALID);
        }

        String lowerName = originalName.toLowerCase();
        if (!(lowerName.endsWith(".xlsx") || lowerName.endsWith(".xls"))) {
            throw new FileValidationException(ONLY_EXCEL_FILES_ARE_ALLOWED);
        }

        String contentType = file.getContentType();
        if (!("application/vnd.ms-excel".equals(contentType) ||
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType))) {

            throw new FileValidationException(INVALID_EXCEL_TYPE);
        }
    }
}
