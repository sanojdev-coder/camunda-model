package com.example.camunda.controller;

import com.example.camunda.dto.OrderRequest;
import com.example.camunda.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderProducer producer;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        String message = request.getOrderId() + ":" + request.getAmount() + ":" + request.getQuantity();
        producer.sendOrderEvent(message);
        return ResponseEntity.accepted().body("Order event published to Kafka successfully.");
    }
}