package com.smartpay.reportservice.repository;

import com.smartpay.reportservice.entity.UserDailySummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDailySummaryRepository extends JpaRepository<UserDailySummary, UUID> {

    Optional<UserDailySummary> findByUserIdAndReportDate(
            String userId,
            LocalDate reportDate
    );

    //Fetches all daily summaries for given month
    List<UserDailySummary>findByUserIdAndReportDateBetweenOrderByReportDateAsc(
            String userId,
            LocalDate from,
            LocalDate to
    );
}
