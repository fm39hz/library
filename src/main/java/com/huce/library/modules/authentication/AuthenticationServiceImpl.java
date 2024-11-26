package com.huce.library.modules.authentication;

import com.huce.library.modules.jwt.JwtTokenProvider;
import com.huce.library.modules.user.CustomUserDetails;
import com.huce.library.modules.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<LoginResponseDto> authenticate(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String name = authentication.getName();
        CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(name);
        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok().header(
                HttpHeaders.AUTHORIZATION,
                token
        ).body(new LoginResponseDto(token));
    }
}
