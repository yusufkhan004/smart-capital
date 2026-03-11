package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateItemDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateResponseDTO;
import com.v1.SmartCapital.entity.PortfolioDetails;
import com.v1.SmartCapital.excel.PortfolioExcelProcessor;
import com.v1.SmartCapital.exception.NotFoundException;
import com.v1.SmartCapital.properties.DocumentPath;
import com.v1.SmartCapital.queue.KafkaProducer;
import com.v1.SmartCapital.repository.PortfolioUploadRepository;
import com.v1.SmartCapital.repository.PortfolioTemplateRepository;
import com.v1.SmartCapital.service.IPortfolioUploadService;
import com.v1.SmartCapital.service.utils.SendPortfolioDataInQueue;
import com.v1.SmartCapital.util.FileNameGenerator;
import com.v1.SmartCapital.validator.FileValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static com.v1.SmartCapital.constants.ErrorMsgConstants.ERROR_FOLDER_PATH_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PortfolioUploadServiceImpl implements IPortfolioUploadService {

    private static final Logger logger = LogManager.getLogger(PortfolioUploadServiceImpl.class);

    private final PortfolioTemplateRepository portfolioTemplateRepository;
    private final PortfolioUploadRepository portfolioDetailsRepository;

    @Autowired
    private DocumentPath documentPath;

    private final PortfolioExcelProcessor portfolioExcelProcessor;
    private final FileValidator fileValidator;
    private final FileNameGenerator fileNameGenerator;

    @Autowired
    KafkaProducer producer;

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
    public PortfolioDetailsDTO uploadPortfolio(PortfolioDetailsDTO dto, MultipartFile file) throws IOException {

        logger.info("PortfolioUploadServiceImpl - Inside uploadPortfolio method");

        fileValidator.validateExcelFile(file);

        String fileName = fileNameGenerator.generateTimestampedFileName(Objects.requireNonNull(file.getOriginalFilename()));

        //TODO  replace portfolioname and portfolio number from master data after done and also save the path in portfolio details table
        String path = savePortfolioFile(file, fileName, dto.getPortfolioName(), dto.getPortfolioNo().toString());

        PortfolioDetails portfolioDetails = PortfolioDetails.builder()
                .portfolioName(dto.getPortfolioName())
                .portfolioNo(dto.getPortfolioNo())
                .versionNo(dto.getVersionNo())
                .filePath(path)
                .cutOffDate(dto.getCutOffDate())
                .executionDate(dto.getExecutionDate())
                .fileName(fileName)
                .build();

        portfolioDetailsRepository.save(portfolioDetails);
        String delUniquePrefix = "Lender+IntermediaryCode+PortIntermediaryNo";

        //TODO  we are sending the portfolio details id in this event and will fetch the path their using portfolio details id
        producer.uploadNewPortfolio(new SendPortfolioDataInQueue(portfolioDetails.getId(), delUniquePrefix, portfolioDetails.getId() , null));

        portfolioExcelProcessor.processExcelAsync(file, portfolioDetails);
        //TODO  above logic should be put in PortfolioUploadAsyncUtils file

        return PortfolioDetailsDTO.builder()
                .portfolioName(portfolioDetails.getPortfolioName())
                .portfolioNo(portfolioDetails.getPortfolioNo())
                .versionNo(portfolioDetails.getVersionNo())
                .cutOffDate(portfolioDetails.getCutOffDate())
                .executionDate(portfolioDetails.getExecutionDate())
                .build();
    }

    protected String savePortfolioFile(MultipartFile file, String fileName, String portfolioName, String portfolioNumber) throws IOException {
        logger.info("PortfolioServiceUtils - Inside savePortfolioFile method");

        // 1. Create the base Path properly using Paths.get to handle slashes automatically
        Path uploadBaseDir = Paths.get(documentPath.getPortfolioUploadDocs());

        // 2. Check if the base directory from your config actually exists
        if (!Files.exists(uploadBaseDir)) {
            throw new NotFoundException(ERROR_FOLDER_PATH_NOT_FOUND + ": " + uploadBaseDir.toString());
        }

        // 3. Build the full target directory path: base/portfolioName/portfolioNumber
        Path targetDir = uploadBaseDir.resolve(portfolioName).resolve(portfolioNumber);

        // 4. Create the full directory structure in one shot (equivalent to mkdir -p)
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        // 5. Define the final file path
        Path filePath = targetDir.resolve(fileName);

        // 6. Write the bytes
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }
}


