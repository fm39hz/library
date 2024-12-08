package com.huce.library.modules.authentication;

import com.huce.library.modules.user.User;

public interface AuthenticationService {
    LoginResponseDto generateToken(LoginRequestDto loginRequestDto);

    LoginResponseDto refreshToken(String refreshToken);

    User getUserByToken(String header);
}
