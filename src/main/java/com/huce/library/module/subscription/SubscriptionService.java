package com.huce.library.module.subscription;

import com.huce.library.module.record.Record;

import java.util.Date;
import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(SubscriptionRequestDto subscriptionDto);

    Date calculateEndDate(Date startDate, Integer period);

    Subscription getSubscription(Long id);

    List<Subscription> getAllSubscriptions();

    Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto);

    Record rentBook(Long bookId, Long userId, Integer period);

    Subscription proceedReturnBook(Long bookId, Long userId);

    void completeReturnBook(Long invoiceId);

    void deleteSubscription(Long id);
}
