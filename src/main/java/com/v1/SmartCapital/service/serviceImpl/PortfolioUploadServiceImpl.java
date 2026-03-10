package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateItemDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateResponseDTO;
import com.v1.SmartCapital.entity.PortfolioDetails;
import com.v1.SmartCapital.excel.PortfolioExcelProcessor;
import com.v1.SmartCapital.repository.PortfolioUploadRepository;
import com.v1.SmartCapital.repository.PortfolioTemplateRepository;
import com.v1.SmartCapital.service.IPortfolioUploadService;
import com.v1.SmartCapital.util.FileNameGenerator;
import com.v1.SmartCapital.validator.FileValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioUploadServiceImpl implements IPortfolioUploadService {

    private static final Logger logger = LogManager.getLogger(PortfolioUploadServiceImpl.class);

    private final PortfolioTemplateRepository portfolioTemplateRepository;
    private final PortfolioUploadRepository portfolioDetailsRepository;

    private final PortfolioExcelProcessor portfolioExcelProcessor;
    private final FileValidator fileValidator;
    private final FileNameGenerator fileNameGenerator;

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

    @Override
    @Transactional
    public PortfolioDetailsDTO uploadPortfolio(PortfolioDetailsDTO dto, MultipartFile file) {

        logger.info("PortfolioUploadServiceImpl - Inside uploadPortfolio method");

        fileValidator.validateExcelFile(file);

        String fileName = fileNameGenerator.generateTimestampedFileName(file.getOriginalFilename());

        PortfolioDetails portfolioDetails = PortfolioDetails.builder()
                .portfolioName(dto.getPortfolioName())
                .portfolioNo(dto.getPortfolioNo())
                .versionNo(dto.getVersionNo())
                .cutOffDate(dto.getCutOffDate())
                .executionDate(dto.getExecutionDate())
                .fileName(fileName)
                .build();

        portfolioDetailsRepository.save(portfolioDetails);

        portfolioExcelProcessor.processExcelAsync(file, portfolioDetails);

        return PortfolioDetailsDTO.builder()
                .portfolioName(portfolioDetails.getPortfolioName())
                .portfolioNo(portfolioDetails.getPortfolioNo())
                .versionNo(portfolioDetails.getVersionNo())
                .cutOffDate(portfolioDetails.getCutOffDate())
                .executionDate(portfolioDetails.getExecutionDate())
                .build();
    }
}


