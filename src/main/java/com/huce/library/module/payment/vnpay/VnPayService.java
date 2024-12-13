package com.huce.library.module.payment.vnpay;

import com.huce.library.configuration.VnPayConfig;
import com.huce.library.module.payment.PaymentMethods;
import com.huce.library.module.payment.PaymentResponseDto;
import com.huce.library.module.payment.PaymentService;
import com.huce.library.module.payment.PaymentStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VnPayService implements PaymentService {
    private final VnPayConfig config;

    public VnPayService(@Qualifier("vnPayConfig") VnPayConfig config) {
        this.config = config;
    }

    @Override
    public String getType() {
        return PaymentMethods.VNPay;
    }

    @Override
    public PaymentResponseDto createPaymentUrl(Long id, float amount, String paymentInfo) {
        VnPayRequestDto dto = new VnPayRequestDto(config, id, amount, paymentInfo);
        String url = dto.toString();
        return new PaymentResponseDto(id, amount, paymentInfo, PaymentStatus.WAITING, url);
    }
}
