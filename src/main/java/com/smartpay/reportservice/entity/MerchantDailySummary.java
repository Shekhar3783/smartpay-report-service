package com.smartpay.reportservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="merchant_daily_summary",
           uniqueConstraints = {
             @UniqueConstraint(columnNames = {
                     "userId",
                     "merchantName",
                     "reportDate"
             })
           })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MerchantDailySummary {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String merchantName;

    @Column(nullable = false)
    private LocalDate reportDate;

    @Column(nullable = false)
    private BigDecimal totalSpent;

    @Column(nullable = false)
    private Integer transactionCount;

    @Column(nullable = false)
    private Instant updateAt;

}
