package com.smartpay.reportservice.service;

import com.smartpay.reportservice.dto.DailySummaryResponse;
import com.smartpay.reportservice.dto.MonthlySummaryResponse;
import com.smartpay.reportservice.entity.UserDailySummary;
import com.smartpay.reportservice.exception.DailySummaryNotFoundException;
import com.smartpay.reportservice.repository.UserDailySummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final UserDailySummaryRepository userDailySummaryRepository;

    @Override
    public DailySummaryResponse getDailySummary(String userId, LocalDate reportDate) {

        log.info("Fetching daily summary for user {} on {} ",userId,reportDate);

        UserDailySummary summary=userDailySummaryRepository.findByUserIdAndReportDate(userId,reportDate)
                .orElseThrow(()->
                        new DailySummaryNotFoundException("No report found for user "+userId));

              //Average=Total Spent / Transaction Count

        BigDecimal average=
                summary.getTotalSpent()
                        .divide(BigDecimal.valueOf(summary.getTransactionCount()),2, RoundingMode.HALF_UP);

        //Convert Entity -> DTO

        return DailySummaryResponse.builder()
                .userId(summary.getUserId())
                .reportDate(summary.getReportDate())
                .totalSpent(summary.getTotalSpent())
                .transactionCount(summary.getTransactionCount())
                .averageTransaction(average)
                .highestTransaction(summary.getHighestTransaction())
                .lastMerchant(summary.getLastMerchant())
                .lastTransactionTime(summary.getLastTransactionTime())
                .build();
    }

    @Override
    public MonthlySummaryResponse getMonthlySummary(String userId, Integer year, Integer month) {

        log.info("Fetching Monthly summary for user {} year {} month {} ",userId,year,month);

        //calculate start date of month
        LocalDate startDate=LocalDate.of(year,month,1);

        //calculate end date of month

        LocalDate endDate=startDate.withDayOfMonth(startDate.lengthOfMonth());

        //fetch all summaries of month
        List<UserDailySummary>summaries=
                userDailySummaryRepository.findByUserIdAndReportDateBetweenOrderByReportDateAsc(userId,
                        startDate,endDate);

        //No data found
        if(summaries.isEmpty()){
            throw new DailySummaryNotFoundException("No monthly report found for user "+userId);
        }

        //Total spent
            BigDecimal totalSpent=summaries.stream()
                    .map(UserDailySummary::getTotalSpent)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);

        //Total transaction count
        Integer totalTransactionCount=summaries.stream()
                .mapToInt(UserDailySummary::getTransactionCount)
                .sum();

        //Number of active days
        Integer days=summaries.size();

        //Highest Spending day
        BigDecimal highestDailySpend=
                summaries.stream()
                        .map(UserDailySummary::getTotalSpent)
                        .max(BigDecimal::compareTo)
                        .orElse(BigDecimal.ZERO);

        //lowest Spending day
        BigDecimal lowestDailySpend=
                summaries.stream()
                        .map(UserDailySummary::getTotalSpent)
                        .min(BigDecimal::compareTo)
                        .orElse(BigDecimal.ZERO);

        //Average Spending per day
        BigDecimal averagePerDay=
                totalSpent.divide(
                        BigDecimal.valueOf(days),2,RoundingMode.HALF_UP);

        //Average Transaction amount
        BigDecimal averageTransaction=
                totalSpent.divide(
                        BigDecimal.valueOf(totalTransactionCount),2,RoundingMode.HALF_UP);

        //build response
        return MonthlySummaryResponse.builder()
                .userId(userId)
                .year(year)
                .month(month)
                .daysWithTransactions(days)
                .totalSpent(totalSpent)
                .totalTransactions(totalTransactionCount)
                .averagePerDay(averagePerDay)
                .averageTransaction(averageTransaction)
                .highestDailySpend(highestDailySpend)
                .lowestDailySpend(lowestDailySpend)
                .build();


    }
}
