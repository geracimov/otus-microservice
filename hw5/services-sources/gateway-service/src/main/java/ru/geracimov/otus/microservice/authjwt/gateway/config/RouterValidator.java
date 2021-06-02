package ru.geracimov.otus.microservice.authjwt.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    private static final List<String> OPEN_API_ENDPOINTS = List.of(
            "/auth/register",
            "/auth/login"
    );

    Predicate<ServerHttpRequest> isSecured = request -> OPEN_API_ENDPOINTS.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
