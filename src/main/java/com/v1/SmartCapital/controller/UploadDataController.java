package com.v1.SmartCapital.controller;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.ResponseDTO;
import com.v1.SmartCapital.service.IUploadDataService;
import com.v1.SmartCapital.util.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.v1.SmartCapital.constants.GeneralMsgConstants.MSG_ALL_TEMPLATES_FETCHED;
import static com.v1.SmartCapital.constants.GeneralMsgConstants.PORTFOLIO_CREATED_SUCCESSFULLY;

@RestController
@RequestMapping("/v1/upload-data")
@RequiredArgsConstructor
public class UploadDataController {

    private static final Logger logger = LogManager.getLogger(UploadDataController.class);

    private final IUploadDataService uploadScreenDataService;

    @GetMapping
    public ResponseEntity<ResponseDTO> uploadScreenData() {
        logger.info("UploadScreenDataController- inside uploadScreenData method");
        return ResponseEntityUtils.get(uploadScreenDataService.prepareUploadData(), MSG_ALL_TEMPLATES_FETCHED);
    }

    @PostMapping("/portfolio")
    public ResponseEntity<ResponseDTO> createPortfolio(
            @RequestBody PortfolioDetailsDTO portfolioDetailsDTO) {

        logger.info("UploadDataController - inside createPortfolio method");

        return ResponseEntityUtils.get(
                uploadScreenDataService.createPortfolio(portfolioDetailsDTO),
                PORTFOLIO_CREATED_SUCCESSFULLY
        );
    }



}
