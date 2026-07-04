package com.smartpay.reportservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreatedEvent {


    /*
     * Unique transaction id published
     * by Transaction Service.
     */
    private String transactionId;

    /*
     * User who performed the transaction.
     */
    private String userId;

    /*
     * Transaction amount.
     */
    private BigDecimal amount;

    /*
     * Merchant where payment happened.
     */
    private String merchantName;

    /*
     * Actual transaction timestamp.
     * Report Service will derive the report date
     * from this instead of using LocalDate.now().
     */
    private OffsetDateTime transactionTime;
}
