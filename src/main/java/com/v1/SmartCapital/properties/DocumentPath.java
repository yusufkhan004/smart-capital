package com.v1.SmartCapital.properties;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
@ConfigurationProperties("smartcapital.document-path")
@Component
public class DocumentPath {
    @NotEmpty
    private String basePath;
    @NotEmpty
    private String portfolioUploadDocs;

    @PostConstruct
    public void setBasePath(){
        portfolioUploadDocs = getBasePath().concat(getPortfolioUploadDocs());
    }
}
