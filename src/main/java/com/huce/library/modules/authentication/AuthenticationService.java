package com.huce.library.modules.authentication;

import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<LoginResponseDto> authenticate(LoginRequestDto loginRequestDto);
}
