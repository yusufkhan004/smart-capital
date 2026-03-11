package com.v1.SmartCapital.queue;

import com.v1.SmartCapital.service.utils.SendPortfolioDataInQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${smartcapital.queue.upload-new-portfolio-queue}")
    private String uploadNewPortfolioTopic;

    @Value("${smartcapital.queue.save-new-portfolio-queue}")
    private String saveNewPortfolioTopic;

    public void uploadNewPortfolio(SendPortfolioDataInQueue sendPortfolioDataInQueue) {
        try {
            logger.info("KafkaProducer - Inside uploadNewPortfolio method");
            kafkaTemplate.send(uploadNewPortfolioTopic, sendPortfolioDataInQueue);
        } catch (Exception e) {
            logger.error("Received Exception during send Message: ", e);
        }
    }
}