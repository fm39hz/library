package com.huce.library.modules.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        var responseDto = authenticationService.generateToken(loginRequest);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestParam String refreshToken) {
        var responseDto = authenticationService.refreshToken(refreshToken);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(responseDto);
    }
}
