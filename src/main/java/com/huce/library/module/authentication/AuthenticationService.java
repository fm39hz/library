package com.huce.library.module.authentication;

public interface AuthenticationService {
    LoginResponseDto generateToken(LoginRequestDto loginRequestDto);

    LoginResponseDto refreshToken(String refreshToken);
}
