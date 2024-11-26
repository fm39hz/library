package com.huce.library.modules.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;

    public CustomAuthenticationManager(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
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
            return userDetailsService.loadUserByUsername(authentication.getName());
        }
    }
}
