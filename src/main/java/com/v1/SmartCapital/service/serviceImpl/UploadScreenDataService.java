package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.dto.BankDTO;
import com.v1.SmartCapital.dto.TemplateDTO;
import com.v1.SmartCapital.dto.UploadScreenResponse;
import com.v1.SmartCapital.entity.Bank;
import com.v1.SmartCapital.entity.Template;
import com.v1.SmartCapital.repository.BankRepository;
import com.v1.SmartCapital.repository.TemplateRepository;
import com.v1.SmartCapital.service.IUploadScreenDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadScreenDataService implements IUploadScreenDataService {

    private final BankRepository bankRepository;
    private  final TemplateRepository templateRepository;
    @Override
    public UploadScreenResponse prepareUploadScreenData() {
        List<Bank> bankList = bankRepository.findAll();
        List<Template> templateList = templateRepository.findAll();

        List<BankDTO> bankDTOList = bankList.stream()
                .map(bank -> new BankDTO(bank.getBankName()))
                .toList();

        List<TemplateDTO> templateDTOList = templateList.stream()
                .map(template -> new TemplateDTO(template.getTemplateName()))
                .toList();

        UploadScreenResponse response = new UploadScreenResponse();
        response.setBankDTOList(bankDTOList);
        response.setTemplateDTOList(templateDTOList);

        return response;
    }
}


