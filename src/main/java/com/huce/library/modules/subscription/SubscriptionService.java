package com.huce.library.modules.subscription;

public interface SubscriptionService {
    Subscription createSubscription(SubscriptionRequestDto subscriptionDto);

    Subscription calculateEndDate(Subscription subscription);

    Subscription getSubscription(Long id);

    Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto);

    void deleteSubscription(Long id);
}
