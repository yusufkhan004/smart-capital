package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.dto.PortfolioDetailsDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateItemDTO;
import com.v1.SmartCapital.dto.PortfolioTemplateResponseDTO;
import com.v1.SmartCapital.entity.PortfolioDetails;
import com.v1.SmartCapital.repository.PortfolioDetailsRepository;
import com.v1.SmartCapital.repository.PortfolioTemplateRepository;
import com.v1.SmartCapital.service.IUploadDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadDataServiceImpl implements IUploadDataService {

    private  final PortfolioTemplateRepository portfolioTemplateRepository;

    private final PortfolioDetailsRepository portfolioDetailsRepository;
    @Override
    public PortfolioTemplateResponseDTO prepareUploadData() {

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
    public PortfolioDetailsDTO createPortfolio(PortfolioDetailsDTO dto) {

        PortfolioDetails portfolioDetails = new PortfolioDetails();

        portfolioDetails.setPortfolioName(dto.getPortfolioName());
        portfolioDetails.setPortfolioNo(dto.getPortfolioNo());
        portfolioDetails.setVersionNo(dto.getVersionNo());
        portfolioDetails.setCutOffDate(dto.getCutOffDate());
        portfolioDetails.setExecutionDate(dto.getExecutionDate());

        portfolioDetails.setCreatedAt(LocalDateTime.now());
        portfolioDetails.setUpdatedAt(LocalDateTime.now());

        PortfolioDetails saved = portfolioDetailsRepository.save(portfolioDetails);

        return new PortfolioDetailsDTO(
                saved.getPortfolioName(),
                saved.getPortfolioNo(),
                saved.getVersionNo(),
                saved.getCutOffDate(),
                saved.getExecutionDate(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }
}


