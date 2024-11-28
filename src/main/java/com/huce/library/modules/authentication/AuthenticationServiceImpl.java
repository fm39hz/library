package com.huce.library.modules.authentication;

import com.huce.library.modules.jwt.JwtTokenProvider;
import com.huce.library.modules.user.CustomUserDetails;
import com.huce.library.modules.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    public AuthenticationServiceImpl(JwtTokenProvider tokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<LoginResponseDto> authenticate(LoginRequestDto loginRequestDto) {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(loginRequestDto.getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var a = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());
        if (a) {
            String token = tokenProvider.generateToken(user);
            return ResponseEntity.ok().header(
                    HttpHeaders.AUTHORIZATION,
                    token
            ).body(new LoginResponseDto(token));
        }
        return ResponseEntity.notFound().build();
    }
}
