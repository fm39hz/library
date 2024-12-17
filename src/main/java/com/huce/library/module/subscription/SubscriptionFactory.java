package com.huce.library.module.subscription;

public class SubscriptionFactory {
    public static String getSubscriptionName(Subscription subscription) {
        return switch (subscription.getPeriod()) {
            case 3 -> Subscriptions.THREE_MONTH;
            case 6 -> Subscriptions.SIX_MONTH;
            case 12 -> Subscriptions.ONE_YEAR;
            default -> Subscriptions.FREE_SUBSCRIPTION;
        };
    }
}
