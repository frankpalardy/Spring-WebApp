package org.example.webapp;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ResilientKafkaConsumerService {

    @KafkaListener(topics = "trades", groupId = "group_id")
    @CircuitBreaker(name = "kafkaConsumer", fallbackMethod = "fallback")
    @Retry(name = "kafkaConsumer")
    @Bulkhead(name = "kafkaConsumer")
    @TimeLimiter(name = "kafkaConsumer")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    public void fallback(String message, Throwable t) {
        System.out.println("Fallback for message: " + message + ", due to: " + t.getMessage());
    }
}