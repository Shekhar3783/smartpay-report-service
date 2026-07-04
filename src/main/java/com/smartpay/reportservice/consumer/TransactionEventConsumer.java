package com.smartpay.reportservice.consumer;

import com.smartpay.reportservice.event.TransactionCreatedEvent;
import com.smartpay.reportservice.service.UserDailySummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransactionEventConsumer {

    private final UserDailySummaryService userDailySummaryService;

    @KafkaListener(topics = "transaction-created", groupId = "report-group")
    public void consume(TransactionCreatedEvent event){
        log.info("Received transaction event for user{} ",event.getUserId());

        userDailySummaryService.processTransaction(event);
    }
}
