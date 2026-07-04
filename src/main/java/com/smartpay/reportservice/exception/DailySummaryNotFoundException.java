package com.smartpay.reportservice.exception;

import org.apache.kafka.common.protocol.types.Field;

public class DailySummaryNotFoundException extends RuntimeException{

    //thrown when no daily summary exists for given user and date.
    public DailySummaryNotFoundException(String message){
        super(message);
    }
}
