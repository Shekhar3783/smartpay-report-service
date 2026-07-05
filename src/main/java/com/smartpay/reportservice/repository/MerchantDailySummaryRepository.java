package com.smartpay.reportservice.repository;

import com.smartpay.reportservice.entity.MerchantDailySummary;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MerchantDailySummaryRepository extends JpaRepository<MerchantDailySummary, UUID> {

    //Find Merchant summary for one user,one merchant, one day. used while processing kafka events.
    Optional<MerchantDailySummary> findByUserIdAndMerchantNameAndReportDate(String userId,
                                                                            String merchantName,
                                                                            LocalDate reoprtDate);

    //fetch merchant summaries for given date range. used by merchant Analytics API.
    List<MerchantDailySummary> findByUserIdAndReportDateBetweenorderByTotalSpentDesc(String userId,
                                                                                     LocalDate from,
                                                                                     LocalDate to);


}
