package ru.geracimov.otus.microservice.authjwt.gateway;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parsinggg")
public class JwtInvalidException extends JwtException {
    public JwtInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
