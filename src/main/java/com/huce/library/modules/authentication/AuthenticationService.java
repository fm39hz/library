package com.huce.library.modules.authentication;

public interface AuthenticationService {
    LoginResponseDto generateToken(LoginRequestDto loginRequestDto);

    LoginResponseDto refreshToken(String refreshToken);
}
