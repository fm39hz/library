package com.huce.library.modules.payment;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentServiceFactory {
    private static final Map<String, PaymentService> servicesCache = new HashMap<>();
    private final List<PaymentService> services;

    public PaymentServiceFactory(List<PaymentService> services) {
        this.services = services;
    }

    public static PaymentService getService(String type) {
        PaymentService service = servicesCache.get(type);
        if (service == null) throw new RuntimeException("Unknown service type: " + type);
        return service;
    }

    @PostConstruct
    public void initMyServiceCache() {
        for (PaymentService service : services) {
            servicesCache.put(service.getType(), service);
        }
    }
}
