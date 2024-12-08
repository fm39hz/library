package com.huce.library.modules.authentication;

import com.huce.library.modules.subscription.SubscriptionResponseDto;
import com.huce.library.modules.user.*;
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
    public ResponseEntity<UserRequestDto> createUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok().body(new UserRequestDto(userService.createUser(user, passwordEncoder.encode(user.getPassword()))));
    }
    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> updateSubscription(
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        return ResponseEntity.ok().body(new UserResponseDto(authenticationService.getUserByToken(authorizationHeader)));
    }
}
