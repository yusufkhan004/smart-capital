package com.v1.SmartCapital.excel;

import com.v1.SmartCapital.entity.PortfolioDetails;
import com.v1.SmartCapital.entity.PortfolioUploadDetails;
import com.v1.SmartCapital.repository.PortfolioUploadDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioExcelProcessor {
    private final PortfolioUploadDetailsRepository portfolioUploadDetailsRepository;
    private final PortfolioRowMapper rowMapper;

    @Async
    public void processExcelAsync(MultipartFile file, PortfolioDetails portfolioDetails) {

        List<PortfolioUploadDetails> batch = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            boolean header = true;

            for (Row row : sheet) {

                if (header) {
                    header = false;
                    continue;
                }

                PortfolioUploadDetails entity = rowMapper.map(row);
                entity.setPortfolioDetails(portfolioDetails);
                batch.add(entity);

                if (batch.size() == 500) {
                    portfolioUploadDetailsRepository.saveAll(batch);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                portfolioUploadDetailsRepository.saveAll(batch);
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel processing failed", e);
        }
    }
}
