package com.example.camunda.kafka;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private RuntimeService runtimeService;

    @KafkaListener(topics = "order-created", groupId = "camunda-group")
    public void consumeOrderEvent(String message) {
        System.out.println("Consumed Kafka Event: " + message);

        String[] parts = message.split(":");
        if (parts.length == 3) {
            String orderId = parts[0];
            Double amount = Double.parseDouble(parts[1]);
            Integer quantity = Integer.parseInt(parts[2]);

            Map<String, Object> variables = new HashMap<>();
            variables.put("orderId", orderId);
            variables.put("amount", amount);
            variables.put("quantity", quantity);

            runtimeService.startProcessInstanceByKey("order-process", variables);
        }
    }
}