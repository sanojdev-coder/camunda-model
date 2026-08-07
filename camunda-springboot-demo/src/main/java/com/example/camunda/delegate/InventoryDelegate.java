package com.example.camunda.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InventoryDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(InventoryDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");
        Integer quantity = (Integer) execution.getVariable("quantity");

        log.info("[1/3] Checking inventory for orderId: {}, Quantity: {}", orderId, quantity);

        if (quantity == null || quantity <= 0) {
            log.warn("Invalid quantity ({}) for orderId: {}. Marking order as invalid.", quantity, orderId);
            execution.setVariable("isValidOrder", false);
        } else {
            log.info("[1/3] Inventory reserved successfully.");
            execution.setVariable("isValidOrder", true);
        }
    }
}