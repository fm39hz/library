package com.huce.library.module.authentication;

import com.huce.library.module.jwt.UserId;
import com.huce.library.module.user.CustomUserDetails;
import com.huce.library.module.user.UserRequestDto;
import com.huce.library.module.user.UserResponseDto;
import com.huce.library.module.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        var responseDto = authenticationService.generateToken(loginRequest);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/refresh/{refreshToken}")
    public ResponseEntity<LoginResponseDto> authenticateUser(@PathVariable String refreshToken) {
        var responseDto = authenticationService.refreshToken(refreshToken);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok().body(new UserResponseDto(userService.createUser(user, passwordEncoder.encode(user.getPassword()))));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> updateSubscription(@UserId Long userId) {
        return ResponseEntity.ok().body(new UserResponseDto(((CustomUserDetails)userService.loadUserById(userId)).getUser()));
    }
}
