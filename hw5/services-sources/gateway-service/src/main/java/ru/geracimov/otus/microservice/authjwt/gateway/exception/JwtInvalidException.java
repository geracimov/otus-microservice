package ru.geracimov.otus.microservice.authjwt.gateway.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Parsing error", value = HttpStatus.BAD_REQUEST)
public class JwtInvalidException extends JwtException {
    public JwtInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
