package ru.geracimov.otus.microservice.authjwt.gateway.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Expired", code = HttpStatus.UNAUTHORIZED)
public class JwtExpiredException extends JwtException {
    public JwtExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
