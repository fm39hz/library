package com.huce.library.modules.subscription;

import com.huce.library.modules.jwt.JwtTokenProvider;
import com.huce.library.modules.user.CustomUserDetails;
import com.huce.library.modules.user.Roles;
import com.huce.library.modules.user.User;
import com.huce.library.modules.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public SubscriptionController(JwtTokenProvider jwtTokenProvider, SubscriptionService subscriptionService, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @PostMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<SubscriptionResponseDto> create(@RequestBody SubscriptionRequestDto subscriptionDto) {
        return ResponseEntity.ok().body(new SubscriptionResponseDto(subscriptionService.createSubscription(subscriptionDto)));
    }

    @PutMapping("/")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody SubscriptionRequestDto subscriptionDto) {
        String token = jwtTokenProvider.extractTokenFromHeader(authorizationHeader);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = ((CustomUserDetails) userService.loadUserById(jwtTokenProvider.getUserIdFromToken(token, true))).getUser();
        Subscription result = subscriptionService.updateSubscription(user.getSubscription().getId(), subscriptionDto);
        return ResponseEntity.ok().body(new SubscriptionResponseDto(result));
    }

    @GetMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@RequestHeader("Authorization") String authorizationHeader) {
        String token = jwtTokenProvider.extractTokenFromHeader(authorizationHeader);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(jwtTokenProvider.getUserIdFromToken(token, true));
        return ResponseEntity.ok().body(new SubscriptionResponseDto(user.getUser().getSubscription()));
    }

    @DeleteMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<SubscriptionResponseDto> cancelSubscription(@RequestHeader("Authorization") String authorizationHeader) {
        String token = jwtTokenProvider.extractTokenFromHeader(authorizationHeader);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(jwtTokenProvider.getUserIdFromToken(token, true));
        subscriptionService.deleteSubscription(user.getUser().getSubscription().getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/rent")
    public ResponseEntity<Date> rentBook(@RequestBody RentRequestDto rentRequestDto, @RequestHeader("Authorization") String authorizationHeader) {
        String token = jwtTokenProvider.extractTokenFromHeader(authorizationHeader);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(jwtTokenProvider.getUserIdFromToken(token, true));
        Date returnDate = subscriptionService.rentBook(rentRequestDto.getBookId(), user.getUser().getId(), rentRequestDto.getPeriod());
        if (returnDate == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(returnDate);
    }
}
