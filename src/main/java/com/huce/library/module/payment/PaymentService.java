package com.huce.library.module.payment;

import com.huce.library.module.invoice.Invoice;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface PaymentService {
    String getType();

    PaymentResponseDto createPaymentUrl(Long id, float amount, String paymentInfo) throws UnsupportedEncodingException;

    Invoice processPayment(Map<String, String> fields);
}
