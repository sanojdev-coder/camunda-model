package com.example.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("orderDelegate")
public class OrderDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(OrderDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");
        log.info("[3/3] Finalizing order: {}", orderId);
    }
}