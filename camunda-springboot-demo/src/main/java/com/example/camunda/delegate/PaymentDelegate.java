package com.example.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("paymentDelegate")
public class PaymentDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(PaymentDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");

        // Fetch the amount from process variables (handles Double, Integer, or String gracefully)
        Object amountObj = execution.getVariable("amount");
        double amount = extractAmount(amountObj);

        log.info("[2/3] Processing payment for orderId: {}, Amount: {}", orderId, amount);

        // Validation Logic: Amount > 0 is SUCCESS, Amount <= 0 is FAILURE
        if (amount > 0) {
            log.info("Payment successful for orderId: {} (Amount: {})", orderId, amount);
            execution.setVariable("paymentSuccessful", true);
        } else {
            log.warn("Payment failed for orderId: {}. Invalid or zero amount: {}. Routing to manual review.", orderId, amount);
            execution.setVariable("paymentSuccessful", false);
        }
    }

    private double extractAmount(Object amountObj) {
        if (amountObj instanceof Number) {
            return ((Number) amountObj).doubleValue();
        } else if (amountObj instanceof String) {
            try {
                return Double.parseDouble((String) amountObj);
            } catch (NumberFormatException e) {
                log.error("Failed to parse amount string: {}", amountObj);
            }
        }
        return 0.0; // Default to 0 if null or unparseable
    }
}