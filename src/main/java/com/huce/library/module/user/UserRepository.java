package com.huce.library.module.user;

import com.huce.library.module.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findBySubscription_Id(Long subscriptionId);

    List<User> Id(Long id);
}
