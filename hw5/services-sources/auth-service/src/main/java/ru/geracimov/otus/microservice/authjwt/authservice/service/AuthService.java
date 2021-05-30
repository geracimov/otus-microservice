package ru.geracimov.otus.microservice.authjwt.authservice.service;

import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserResponse;

public interface AuthService {

    UserResponse register(UserCreateRequest userCreateRequest);

    AuthResponse authenticate(AuthRequest authRequest);

}
