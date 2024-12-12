package com.huce.library.modules.subscription;

import java.util.Date;

public interface SubscriptionService {
    Subscription createSubscription(SubscriptionRequestDto subscriptionDto);

    Date calculateEndDate(Date startDate, Integer period);

    Subscription getSubscription(Long id);

    Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto);

    void deleteSubscription(Long id);
}
