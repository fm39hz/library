package com.huce.library.module.authentication;

import com.huce.library.module.user.User;

public interface AuthenticationService {
    LoginResponseDto generateToken(LoginRequestDto loginRequestDto);

    LoginResponseDto refreshToken(String refreshToken);

    User getUserByToken(String header);
}
