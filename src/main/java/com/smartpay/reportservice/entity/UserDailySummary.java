package com.smartpay.reportservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_daily_summary",
              uniqueConstraints = {
               @UniqueConstraint(columnNames = {"user_id","report_date"})
              })

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDailySummary {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "total_spent", nullable = false)
    private BigDecimal totalSpent;

    @Column(name = "transaction_count", nullable = false)
    private Integer transactionCount;

    @Column(name = "highest_transaction", nullable = false)
    private BigDecimal highestTransaction;

    @Column(name = "last_merchant")
    private String lastMerchant;

    @Column(name = "last_transaction_time")
    private OffsetDateTime lastTransactionTime;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
