package com.huce.library.module.user;

import com.huce.library.module.subscription.Subscription;
import com.huce.library.module.subscription.SubscriptionRequestDto;
import com.huce.library.module.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SubscriptionService subscriptionService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, SubscriptionService subscriptionService) {
        this.userRepository = userRepository;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public UserDetails loadUserById(long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserRequestDto user, String encodedPassword) {
        Subscription subscription = new Subscription();
        User newUser = new User();
        subscription.setUser(newUser);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRole(user.getRole());
        User resultUser = userRepository.save(newUser);
        Subscription newSubscription = subscriptionService.createSubscription(new SubscriptionRequestDto(subscription));
        resultUser.setSubscription(newSubscription);
        return userRepository.save(resultUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
