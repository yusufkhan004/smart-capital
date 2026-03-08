package com.v1.SmartCapital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.ResponseDTO;
import com.v1.SmartCapital.service.IPortfolioUploadService;
import com.v1.SmartCapital.util.ResponseEntityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.v1.SmartCapital.constants.GeneralMsgConstants.*;

@RestController
@RequestMapping("/v1/upload-data")
@RequiredArgsConstructor
public class PortfolioUploadController {

    private static final Logger logger = LogManager.getLogger(PortfolioUploadController.class);

    private final IPortfolioUploadService uploadScreenDataService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<ResponseDTO> uploadScreenData() {
        logger.info("PortfolioUploadController- inside uploadScreenData method");
        return ResponseEntityUtils.get(uploadScreenDataService.prepareUploadData(), MSG_ALL_TEMPLATES_FETCHED_SUCCESSFULLY);
    }

    @PostMapping(value = "/portfolio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> uploadPortfolio(
            @Valid
            @RequestPart("portfolioDetailsDTO") String portfolioJson,
            @RequestPart("file") MultipartFile file) throws Exception {

        logger.info("PortfolioUploadController- inside uploadPortfolio method");

        PortfolioDetailsDTO dto = objectMapper.readValue(portfolioJson, PortfolioDetailsDTO.class);
        return ResponseEntityUtils.get(
                uploadScreenDataService.uploadPortfolio(dto, file),
                PORTFOLIO_UPLOADED_SUCCESSFULLY
        );
    }



}
