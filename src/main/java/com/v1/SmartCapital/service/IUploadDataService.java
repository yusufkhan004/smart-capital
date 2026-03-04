package com.v1.SmartCapital.service;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateResponseDTO;

public interface IUploadDataService {
    PortfolioTemplateResponseDTO prepareUploadData();

    PortfolioDetailsDTO createPortfolio(PortfolioDetailsDTO portfolioDetailsDTO);

}
