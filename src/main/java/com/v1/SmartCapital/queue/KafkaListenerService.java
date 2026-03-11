package com.v1.SmartCapital.queue;


import com.v1.SmartCapital.service.utils.PortfolioUploadAsyncUtils;
import com.v1.SmartCapital.service.utils.SendPortfolioDataInQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

    @Autowired
    PortfolioUploadAsyncUtils portfolioUploadAsyncUtils;
    // A default group ID is required for Kafka consumers
    private static final String GROUP_ID = "smartcapital-group";

    @KafkaListener(topics = "${smartcapital.queue.upload-new-portfolio-queue}", groupId = GROUP_ID)
    public void uploadNewPortfolio(@Payload SendPortfolioDataInQueue sendPortfolioDataInQueue) {
        logger.info("KafkaListenerService - Inside uploadNewPortfolio method");
        portfolioUploadAsyncUtils.portfolioUploadAsyncNew(sendPortfolioDataInQueue.getPortfolioDetailsId(), sendPortfolioDataInQueue.getDelUniqueIdPrefix(),
                sendPortfolioDataInQueue.getPortJobDetailsId(), sendPortfolioDataInQueue.getTokenResponse());
    }
}