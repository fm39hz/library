package com.huce.library.module.invoice;

import com.huce.library.module.payment.PaymentStatus;
import com.huce.library.module.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class InvoiceResponseDto {
    private Long id;
    private String description;
    private Float amount;
    private Date createTime;
    private String status;
    private Long subscriptionId;

    public InvoiceResponseDto(Invoice invoice) {
        setId(invoice.getId());
        setDescription(invoice.getDescription());
        setAmount(invoice.getAmount());
        setCreateTime(invoice.getCreateTime());
        setStatus(invoice.getStatus());
        setSubscriptionId(invoice.getSubscription().getId());
    }
}
