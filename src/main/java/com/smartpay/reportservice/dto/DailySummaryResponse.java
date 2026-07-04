package com.smartpay.reportservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
public class DailySummaryResponse {

    private String userId;

    private LocalDate reportDate;

    private BigDecimal totalSpent;

    private Integer transactionCount;

    private BigDecimal averageTransaction;

    private BigDecimal highestTransaction;

    private String lastMerchant;

    private OffsetDateTime lastTransactionTime;
}
