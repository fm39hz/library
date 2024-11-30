package com.huce.library.modules.payment;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    String getType();

    PaymentResponseDto createPaymentUrl(Long id, int amount, String paymentInfo) throws UnsupportedEncodingException;
}
