package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateItemDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateResponseDTO;
import com.v1.SmartCapital.entity.PortfolioDetails;
import com.v1.SmartCapital.exception.FileValidationException;
import com.v1.SmartCapital.repository.PortfolioUploadRepository;
import com.v1.SmartCapital.repository.PortfolioTemplateRepository;
import com.v1.SmartCapital.service.IFileStorageService;
import com.v1.SmartCapital.service.IPortfolioUploadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.v1.SmartCapital.constants.ErrorMsgConstants.UPLOADED_FILE_IS_MISSING_OR_EMPTY;
import static com.v1.SmartCapital.constants.GeneralMsgConstants.FILE_NAME_IS_INVALID;
import static com.v1.SmartCapital.constants.GeneralMsgConstants.ONLY_EXCEL_FILES_ARE_ALLOWED;

@Service
@RequiredArgsConstructor
public class PortfolioUploadServiceImpl implements IPortfolioUploadService {

    private static final Logger logger = LogManager.getLogger(PortfolioUploadServiceImpl.class);

    private  final PortfolioTemplateRepository portfolioTemplateRepository;
    private final PortfolioUploadRepository portfolioDetailsRepository;
    private final IFileStorageService fileStorageService;

    @Override
    public PortfolioTemplateResponseDTO prepareUploadData() {

        logger.info("PortfolioUploadServiceImpl - Inside prepareUploadData method");

        List<PortfolioTemplateItemDTO> templateDTOList =
                portfolioTemplateRepository.findAll()
                        .stream()
                        .map(template -> new PortfolioTemplateItemDTO(
                                template.getId(),
                                template.getTemplateName()
                        ))
                        .toList();

        return new PortfolioTemplateResponseDTO(templateDTOList);
    }

    private void validateExcelFile(MultipartFile file) {
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

            throw new FileValidationException("Invalid Excel file type");
        }
    }

    private String generateTimestampedFileName(String originalName) {
        int dotIndex = originalName.lastIndexOf(".");
        String baseName = (dotIndex == -1) ? originalName : originalName.substring(0, dotIndex);
        String extension = (dotIndex == -1) ? "" : originalName.substring(dotIndex);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return baseName + "_" + timestamp + extension;
    }

    @Override
    @Transactional
    public PortfolioDetailsDTO uploadPortfolio(PortfolioDetailsDTO dto, MultipartFile file) {

        logger.info("PortfolioUploadServiceImpl - Inside uploadPortfolio method");

        validateExcelFile(file);

        String fileName = generateTimestampedFileName(file.getOriginalFilename());
        String filePath = "uploads/" + fileName;

        PortfolioDetails portfolioDetails = PortfolioDetails.builder()
                .portfolioName(dto.getPortfolioName())
                .portfolioNo(dto.getPortfolioNo())
                .versionNo(dto.getVersionNo())
                .cutOffDate(dto.getCutOffDate())
                .executionDate(dto.getExecutionDate())
                .fileName(fileName)
                .fileType(file.getContentType())
                .filePath(filePath)
                .build();

        portfolioDetailsRepository.save(portfolioDetails);

        fileStorageService.storeFileAsync(file, filePath);

        return PortfolioDetailsDTO.builder()
                .portfolioName(portfolioDetails.getPortfolioName())
                .portfolioNo(portfolioDetails.getPortfolioNo())
                .versionNo(portfolioDetails.getVersionNo())
                .cutOffDate(portfolioDetails.getCutOffDate())
                .executionDate(portfolioDetails.getExecutionDate())
                .build();
    }
}


