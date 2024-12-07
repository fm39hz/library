package com.huce.library.modules.payment.vnpay;

import com.huce.library.configuration.VnPayConfig;
import com.huce.library.modules.payment.PaymentResponseDto;
import com.huce.library.modules.payment.PaymentService;
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
        return "VnPay";
    }

    @Override
    public PaymentResponseDto createPaymentUrl(Long id, int amount, String paymentInfo) {
        VnPayRequestDto dto = new VnPayRequestDto(config, id, amount, paymentInfo);
        String url = dto.toString();
        return new PaymentResponseDto(id, amount, paymentInfo, "waiting", url);
    }
}
