package com.smartpay.reportservice.controller;

import com.smartpay.reportservice.dto.DailySummaryResponse;
import com.smartpay.reportservice.dto.MonthlySummaryResponse;
import com.smartpay.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/users/{userId}/daily")
    public DailySummaryResponse getDailySummary(@PathVariable String userId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){

        return reportService.getDailySummary(userId,date);
    }


    /*
     * Returns monthly spending analytics.
     * Example:
      * GET
     * /api/reports/users/USER014/monthly?year=2026&month=7
     */
    @GetMapping("/users/{userId}/monthly")
    public MonthlySummaryResponse getMonthlySummary(@PathVariable String userId,
                                                    @RequestParam Integer year,
                                                    @RequestParam Integer month) {

        return reportService.getMonthlySummary(userId, year, month);

    }
}
