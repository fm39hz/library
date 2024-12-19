package com.huce.library.module.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDto {
    private Long invoiceId;
    private Float amount;
    private String paymentInfo;
    private String status;
    private String paymentUrl;
}
