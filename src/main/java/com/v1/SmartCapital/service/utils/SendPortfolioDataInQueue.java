package com.v1.SmartCapital.service.utils;

import com.v1.SmartCapital.response.TokenResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendPortfolioDataInQueue implements Serializable {

    Long portfolioDetailsId;
    String delUniqueIdPrefix;
    Long portJobDetailsId;
    TokenResponse tokenResponse;

    public SendPortfolioDataInQueue(Long portfolioDetailsId, Long portJobDetailsId, TokenResponse tokenResponse) {
        super();
        this.portfolioDetailsId = portfolioDetailsId;
        this.portJobDetailsId = portJobDetailsId;
        this.tokenResponse = tokenResponse;
    }

    public SendPortfolioDataInQueue(Long portfolioDetailsId, TokenResponse tokenResponse) {
        this.portfolioDetailsId = portfolioDetailsId;
        this.tokenResponse = tokenResponse;
    }

}
