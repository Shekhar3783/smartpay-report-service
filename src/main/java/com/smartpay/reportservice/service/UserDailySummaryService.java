package com.smartpay.reportservice.service;

import com.smartpay.reportservice.event.TransactionCreatedEvent;

public interface UserDailySummaryService {
    /*
     * Processes every transaction received from Kafka.
     *
     * Later this method will:
     * 1. Find today's summary for the user.
     * 2. Create a new summary if it doesn't exist.
     * 3. Update the existing summary if it exists.
     */

    void processTransaction(TransactionCreatedEvent event);

}
