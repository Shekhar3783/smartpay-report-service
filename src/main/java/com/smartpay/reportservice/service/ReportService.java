package com.smartpay.reportservice.service;

import com.smartpay.reportservice.dto.DailySummaryResponse;
import com.smartpay.reportservice.dto.MonthlySummaryResponse;

import java.time.LocalDate;

public interface ReportService {

    //Returns daily spending analytics for given user.
    DailySummaryResponse getDailySummary(String userId, LocalDate reportDate);

    //Returns monthly spending analytics.
    MonthlySummaryResponse getMonthlySummary(String userId, Integer year, Integer month);
}
