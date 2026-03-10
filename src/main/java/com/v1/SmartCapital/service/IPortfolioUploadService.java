package com.v1.SmartCapital.service;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IPortfolioUploadService {
    PortfolioTemplateResponseDTO prepareUploadData();

    PortfolioDetailsDTO uploadPortfolio(PortfolioDetailsDTO portfolioDetailsDTO, MultipartFile multipartFile);

}
