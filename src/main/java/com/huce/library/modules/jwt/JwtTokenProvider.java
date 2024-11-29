package com.huce.library.modules.jwt;

import com.huce.library.modules.user.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.expiration}")
    public long JWT_EXPIRATION;
    @Value("${jwt.refreshExpiration}")
    public long JWT_REFRESH_EXPIRATION;
    @Value("${jwt.secret}")
    private String JWT_SECRET;
    @Value("${jwt.refreshSecret}")
    private String JWT_REFRESH_SECRET;

    private String generateToken(CustomUserDetails userDetails, Boolean isAccessToken) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (isAccessToken ? JWT_EXPIRATION : JWT_REFRESH_EXPIRATION));
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, isAccessToken ? JWT_SECRET : JWT_REFRESH_SECRET)
                .compact();
    }

    public String generateAccessToken(CustomUserDetails userDetails) {
        return generateToken(userDetails, true);
    }

    public String generateRefreshToken(CustomUserDetails userDetails) {
        return generateToken(userDetails, false);
    }

    public Long getUserIdFromToken(String token, boolean isAccessToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(isAccessToken ? JWT_SECRET : JWT_REFRESH_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        return validateToken(authToken, JWT_SECRET);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, JWT_REFRESH_SECRET);
    }

    public String extractTokenFromHeader(String authorizationHeader) {
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        return token;
    }

    private boolean validateToken(String token, String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature.");
        }
        return false;
    }
}
