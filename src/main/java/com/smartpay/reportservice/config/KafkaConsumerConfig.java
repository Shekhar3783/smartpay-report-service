//package com.smartpay.reportservice.config;
//
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Bean
//    public Map<String, Object> consumerConfigs(){
//
//        Map<String,Object> props=new HashMap<>();
//
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,"report-group");
//
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        props.put(org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES,"*");
//        return props;
//    }
//}
