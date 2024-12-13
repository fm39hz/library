package com.huce.library.module.authentication;

import com.huce.library.module.jwt.JwtTokenProvider;
import com.huce.library.module.user.CustomUserDetails;
import com.huce.library.module.user.User;
import com.huce.library.module.user.UserService;
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
    public LoginResponseDto generateToken(LoginRequestDto loginRequestDto) {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(loginRequestDto.getUsername());
        if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            String token = tokenProvider.generateAccessToken(user);
            String refreshToken = tokenProvider.generateRefreshToken(user);
            Long expiresIn = tokenProvider.JWT_EXPIRATION;
            Long refreshExpiresIn = tokenProvider.JWT_REFRESH_EXPIRATION;
            return new LoginResponseDto(token, refreshToken, expiresIn, refreshExpiresIn);
        }
        return null;
    }

    @Override
    public LoginResponseDto refreshToken(String refreshToken) {
        if (tokenProvider.validateRefreshToken(refreshToken)) {
            CustomUserDetails user = (CustomUserDetails) userService.loadUserById(tokenProvider.getUserIdFromToken(refreshToken, false));
            String token = tokenProvider.generateAccessToken(user);
            String _refreshToken = tokenProvider.generateRefreshToken(user);
            Long expiresIn = tokenProvider.JWT_EXPIRATION;
            Long refreshExpiresIn = tokenProvider.JWT_REFRESH_EXPIRATION;
            return new LoginResponseDto(token, _refreshToken, expiresIn, refreshExpiresIn);
        }
        return null;
    }

    @Override
    public User getUserByToken(String header) {
        String token = tokenProvider.extractTokenFromHeader(header);
        if (token == null) {
            return null;
        }
        return ((CustomUserDetails) userService.loadUserById(tokenProvider.getUserIdFromToken(token, true))).getUser();
    }
}
