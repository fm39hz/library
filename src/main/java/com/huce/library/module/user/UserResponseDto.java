package com.huce.library.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Long subscriptionId;

    public UserResponseDto(User user) {
        setId(user.getId());
        setName(user.getUsername());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        setRole(user.getRole());
        setSubscriptionId(user.getSubscription().getId());
    }
}
