package com.huce.library.modules.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private Long id;

    private String username;

    private String password;

    private String role;

    private Long subscriptionId;

    public UserRequestDto(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setRole(user.getRole());
        setSubscriptionId(user.getSubscription().getId());
    }
}
