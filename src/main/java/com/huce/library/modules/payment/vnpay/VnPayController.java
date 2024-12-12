package com.huce.library.modules.payment.vnpay;

import com.huce.library.modules.payment.PaymentMethods;
import com.huce.library.modules.payment.PaymentResponseDto;
import com.huce.library.modules.payment.PaymentService;
import com.huce.library.modules.payment.PaymentServiceFactory;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/payment-vnpay")
public class VnPayController {
    private final PaymentService paymentService;

    public VnPayController() {
        paymentService = PaymentServiceFactory.getService(PaymentMethods.VNPay);
    }

    @GetMapping("/selectPaymentMethod/{id}")
    public PaymentResponseDto selectPaymentMethod(
            @PathVariable Long id,
            @RequestParam Integer amount,
            @RequestParam String info
    ) throws UnsupportedEncodingException {
        return paymentService.createPaymentUrl(id, amount, info);
    }
}
