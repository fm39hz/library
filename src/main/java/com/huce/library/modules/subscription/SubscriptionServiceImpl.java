package com.huce.library.modules.subscription;

import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.modules.user.User;
import com.huce.library.modules.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Subscription createSubscription(SubscriptionRequestDto subscriptionDto) {
        Subscription subscription = new Subscription();
        if (userRepository.findById(subscriptionDto.getUser()).isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        User user = userRepository.findById(subscriptionDto.getUser()).get();
        subscription.setUser(user);
        subscription.setStartDate(new Date());
        subscription.setPeriod(subscriptionDto.getPeriod());
        subscription.setStatus(subscriptionDto.getStatus());
        subscription.setRentedBooks(new ArrayList<>());
        subscription = calculateEndDate(subscription);
        user.setSubscription(subscription);
        userRepository.save(user);
        return subscription;
    }

    @Override
    public Subscription calculateEndDate(Subscription subscription) {
        if (subscription.getPeriod() == null || Objects.equals(subscription.getStatus(), "pending")) {
            subscription.setEndDate(subscription.getStartDate());
            subscription.setPeriod(-1);
            return subscription;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscription.getStartDate());
        calendar.add(Calendar.MONTH, subscription.getPeriod());
        subscription.setEndDate(calendar.getTime());
        return subscription;
    }

    @Override
    public Subscription getSubscription(Long id) {
        if (subscriptionRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Subscription not found");
        }
        Subscription subscription = subscriptionRepository.findById(id).get();
        if (Objects.equals(subscription.getStatus(), "cancelled")) {
            throw new ResourceNotFoundException("Subscription cancelled");
        }
        return subscriptionRepository.findById(id).get();
    }

    @Override
    public Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto) {
        Subscription subscription = getSubscription(id);
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setPeriod(subscriptionDto.getPeriod());
        subscription.setStatus(subscriptionDto.getStatus());
        return subscriptionRepository.save(calculateEndDate(subscription));
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = getSubscription(id);
        subscription.setStatus("cancelled");
    }
}
