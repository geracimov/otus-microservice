package ru.geracimov.otus.microservice.authjwt.authservice.service;

import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateResponse;

public interface AuthService {

    UserCreateResponse register(UserCreateRequest userCreateRequest);

    AuthResponse authenticate(AuthRequest authRequest);

}
