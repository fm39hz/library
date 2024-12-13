package com.huce.library.modules.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SubscriptionRequestDto {
    private Long user;
    private Date startDate;
    private Integer period;
    private String status;
    private Float lateFee;
    private Float lateFeePercent;

    public SubscriptionRequestDto(Subscription subscription) {
        setUser(subscription.getUser().getId());
        setStartDate(subscription.getStartDate());
        setPeriod(subscription.getPeriod());
        setStatus(subscription.getStatus());
        setLateFee(subscription.getLateFee());
        setLateFeePercent(subscription.getLateFeePercent());
    }
}
