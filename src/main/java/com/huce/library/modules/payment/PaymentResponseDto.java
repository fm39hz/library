package com.huce.library.modules.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDto {
    private Long paymentId;
    private Integer amount;
    private String paymentInfo;
    private String status;
    private String paymentUrl;
}
