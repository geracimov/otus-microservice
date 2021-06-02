package ru.geracimov.otus.microservice.authjwt.gateway.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.microservice.authjwt.gateway.exception.JwtExpiredException;
import ru.geracimov.otus.microservice.authjwt.gateway.exception.JwtInvalidException;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        var jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        try {
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token Expired", e);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtInvalidException("JWT parsing exception", e);
        }

    }

    private boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return isTokenExpired(token);
    }

}
