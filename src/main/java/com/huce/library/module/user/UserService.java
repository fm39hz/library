package com.huce.library.module.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserById(long id);

    User createUser(UserRequestDto user, String encodedPassword);

    void deleteUserById(long id);
}
