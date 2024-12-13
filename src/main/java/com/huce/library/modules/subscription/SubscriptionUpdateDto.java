package com.huce.library.modules.subscription;

import com.huce.library.modules.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubscriptionUpdateDto {
    private Long id;
    private User user;
    private Integer period;
    private String status;
    private Float lateFee;
    private Float lateFeePercent;

    public SubscriptionUpdateDto(Subscription subscription) {
        setId(subscription.getId());
        setUser(subscription.getUser());
        setStatus(subscription.getStatus());
        setLateFee(subscription.getLateFee());
        setLateFeePercent(subscription.getLateFeePercent());
    }
}
