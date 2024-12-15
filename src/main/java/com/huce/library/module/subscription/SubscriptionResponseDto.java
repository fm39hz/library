package com.huce.library.module.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SubscriptionResponseDto {
    private Long id;
    private Long user;
    private Date startDate;
    private Date endDate;
    private Integer period;
    private Integer rentLimit;
    private String status;
    private Float lateFee;
    private Float lateFeePercent;
    private Float remainingFee;

    public SubscriptionResponseDto(Subscription subscription) {
        setId(subscription.getId());
        setUser(subscription.getUser().getId());
        setStartDate(subscription.getStartDate());
        setEndDate(subscription.getEndDate());
        setPeriod(subscription.getPeriod());
        setRentLimit(subscription.getRentLimit());
        setStatus(subscription.getStatus());
        setLateFee(subscription.getLateFee());
        setLateFeePercent(subscription.getLateFeePercent());
        setRemainingFee(subscription.getRemainingFee());
    }
}
