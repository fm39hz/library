package com.huce.library.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String role;
    private Long subscriptionId;
    public UserDto(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setRole(user.getRole());
        setSubscriptionId(user.getSubscription().getId());
    }
}
