package com.smartpay.reportservice.service;

import com.smartpay.reportservice.entity.UserDailySummary;
import com.smartpay.reportservice.event.TransactionCreatedEvent;
import com.smartpay.reportservice.repository.UserDailySummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDailySummaryServiceImpl implements UserDailySummaryService{

    private  final UserDailySummaryRepository userDailySummaryRepository;

    @Override
    public void processTransaction(TransactionCreatedEvent event) {

       log.info("Processing transaction for user {} amount {} ",event.getUserId(),event.getAmount());

        LocalDate reportDate=event.getTransactionTime().toLocalDate();

        Optional<UserDailySummary> optionalSummary=userDailySummaryRepository.findByUserIdAndReportDate(event.getUserId(), reportDate);

        UserDailySummary summary;

        if(optionalSummary.isPresent()){
            log.info("updating existing summary");
            summary=optionalSummary.get();

            //calculate total amount spent
            summary.setTotalSpent(summary.getTotalSpent().add(event.getAmount()));

            //calculate total transactions
            summary.setTransactionCount(summary.getTransactionCount()+1);

           //update highest transaction if current amount is larger
           if(event.getAmount().compareTo(summary.getHighestTransaction())>0){
               summary.setHighestTransaction(event.getAmount());
           }

           summary.setLastMerchant(event.getMerchantName());
           summary.setLastTransactionTime(event.getTransactionTime());
           summary.setUpdatedAt(OffsetDateTime.now());

        }
        //No summary found create first summary for today
        else{
        log.info("Creating new Summary");
        summary=UserDailySummary.builder()
                        .userId(event.getUserId())
                                .reportDate(reportDate)
                                        .totalSpent(event.getAmount())
                                                .transactionCount(1)
                                                        .highestTransaction(event.getAmount())
                                                                .lastMerchant(event.getMerchantName())
                                                                        .lastTransactionTime(event.getTransactionTime())
                                                                                .updatedAt(OffsetDateTime.now())
                                                                                   .build();
        }

        userDailySummaryRepository.save(summary);

        log.info("Daily summary saved successfully for user {} ",event.getUserId());

    }
}
