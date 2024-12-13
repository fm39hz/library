package com.huce.library.module.subscription;

import java.util.Date;
import com.huce.library.module.invoice.Invoice;

public interface SubscriptionService {
    Subscription createSubscription(SubscriptionRequestDto subscriptionDto);

    Date calculateEndDate(Date startDate, Integer period);

    Subscription getSubscription(Long id);

    Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto);

    Invoice rentBook(Long bookId, Long userId, Integer period);

    Subscription proceedReturnBook(Long bookId, Long userId);

    void completeReturnBook(Long invoiceId);

    void deleteSubscription(Long id);
}
