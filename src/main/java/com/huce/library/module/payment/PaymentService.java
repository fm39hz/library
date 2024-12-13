package com.huce.library.module.payment;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    String getType();

    PaymentResponseDto createPaymentUrl(Long id, float amount, String paymentInfo) throws UnsupportedEncodingException;
}
