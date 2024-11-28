package com.huce.library.modules.authentication;

import com.huce.library.modules.user.UserService;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserService userService;

    public CustomAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new CustomAuthenticationToken(authentication);
    }

    private class CustomAuthenticationToken extends AbstractAuthenticationToken {
        private final Authentication authentication;

        public CustomAuthenticationToken(Authentication authentication) {
            super(authentication.getAuthorities());
            this.authentication = authentication;
        }

        @Override
        public Object getCredentials() {
            return authentication.getName();
        }

        @Override
        public Object getPrincipal() {
            return userService.loadUserByUsername(authentication.getName());
        }
    }
}
