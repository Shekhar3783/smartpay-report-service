package com.smartpay.reportservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MonthlySummaryResponse {

    //user for whom report get generated
    private String userId;

    private Integer year;

    private Integer month;

    //No of days user made transactions
    private Integer daysWithTransactions;

    //Total Spending this month
    private BigDecimal totalSpent;

    private Integer totalTransactions;

    private BigDecimal averagePerDay;

    private BigDecimal averageTransaction;

    private BigDecimal highestDailySpend;

    private BigDecimal lowestDailySpend;
}
