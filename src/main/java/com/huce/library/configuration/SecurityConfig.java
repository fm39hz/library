package com.huce.library.configuration;

import com.huce.library.module.authentication.CustomAuthenticationManager;
import com.huce.library.module.jwt.JwtAuthenticationFilter;
import com.huce.library.module.jwt.JwtTokenProvider;
import com.huce.library.module.user.Roles;
import com.huce.library.module.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return new CustomAuthenticationManager(userService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(false);
        configuration.addAllowedOrigin("*");
//        configuration.addAllowedOriginPattern("http://localhost:*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.applyPermitDefaultValues();
        return request -> configuration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/book/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/author/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/subscription/**").permitAll()
                        .requestMatchers("api/v1/subscription/**").hasAuthority(Roles.USER)
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .anyRequest().hasAuthority(Roles.ADMIN)
                )
                .httpBasic(withDefaults())
                .addFilterBefore(new JwtAuthenticationFilter(userService, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
