package ru.geracimov.otus.microservice.authjwt.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserCreateResponse> register(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(authService.register(userCreateRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

}
