package com.huce.library.module.payment.vnpay;

import com.huce.library.configuration.VnPayConfig;
import com.huce.library.exception.CompletedInvoiceException;
import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.module.invoice.Invoice;
import com.huce.library.module.invoice.InvoiceRepository;
import com.huce.library.module.payment.PaymentMethods;
import com.huce.library.module.payment.PaymentResponseDto;
import com.huce.library.module.payment.PaymentService;
import com.huce.library.module.payment.PaymentStatus;
import org.apache.commons.codec.digest.HmacUtils;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class VnPayService implements PaymentService {
    private final VnPayConfig config;
    private final InvoiceRepository invoiceRepository;

    public VnPayService(@Qualifier("vnPayConfig") VnPayConfig config, InvoiceRepository invoiceRepository) {
        this.config = config;
        this.invoiceRepository = invoiceRepository;
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

    @Transactional
    @Override
    public Invoice processPayment(Map<String, String> fields) {
        String vnp_SecureHash = fields.get("vnp_SecureHash");
        String vnp_Status = fields.get("vnp_SecureHash");
        Long id = Long.valueOf(fields.get("vnp_TxnRef"));
        if (invoiceRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Cannot find invoice");
        }
        Invoice invoice = invoiceRepository.findById(id).get();
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=')
                        .append(URLDecoder.decode(fieldValue, StandardCharsets.US_ASCII)).append('&');
            }
        }
        hashData.setLength(hashData.length() - 1);
        String computedHash = new HmacUtils("HmacSHA512", config.getHashSecret()).hmacHex(hashData.toString());
        switch (invoice.getStatus()) {
            case PaymentStatus.WAITING:
                invoice.setStatus(!computedHash.equals(vnp_SecureHash) && Objects.equals(vnp_Status, "00") ? PaymentStatus.SUCCESS : PaymentStatus.FAIL);
                break;
            case PaymentStatus.FAIL:
            case PaymentStatus.SUCCESS:
                throw new CompletedInvoiceException("Invoice has been proceeded");
        }
        return invoiceRepository.save(invoice);
    }
}
