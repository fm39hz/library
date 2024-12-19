package com.huce.library.module.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDetails loadUserById(long id);

    List<User> getAllUsers();

    User createUser(UserRequestDto user, String encodedPassword);

    void deleteUserById(long id);
}
