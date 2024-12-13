package com.huce.library.module.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

@Aspect
@Component
public class UserIdAspect {

    private final JwtTokenProvider jwtTokenProvider;

    public UserIdAspect(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Around("execution(* com.huce.library.module.*..*(.., @UserId (*), ..))")
    public Object extractUserId(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String authorizationHeader = request.getHeader("Authorization");

        String token = jwtTokenProvider.extractTokenFromHeader(authorizationHeader);
        Long userId = null;
        if (token != null) {
            userId = jwtTokenProvider.getUserIdFromToken(token, true);
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(UserId.class)) {
                args[i] = userId;
            }
        }

        return joinPoint.proceed(args);
    }
}
