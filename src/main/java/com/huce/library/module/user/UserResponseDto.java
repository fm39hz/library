package com.huce.library.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String role;
    private Long subscriptionId;

    public UserResponseDto(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        setRole(user.getRole());
        setSubscriptionId(user.getSubscription().getId());
    }
}
