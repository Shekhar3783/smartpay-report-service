package com.smartpay.reportservice.service;

import com.smartpay.reportservice.event.TransactionCreatedEvent;
import com.smartpay.reportservice.repository.UserDailySummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDailySummaryServiceImpl implements UserDailySummaryService{

    private  final UserDailySummaryRepository userDailySummaryRepository;

    @Override
    public void processTransaction(TransactionCreatedEvent event) {

       log.info("Processing transaction for user {} amount {} ",event.getUserId(),event.getAmount());

    }
}
