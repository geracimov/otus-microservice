package ru.geracimov.otus.microservice.authjwt.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.exception.UnauthorizedException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final JwtUtil jwt;

    @Override
    public UserCreateResponse register(UserCreateRequest userCreateRequest) {
        return restTemplate.postForObject("http://user-service:9001/user", userCreateRequest, UserCreateResponse.class);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            ResponseEntity<UserCreateResponse> response = restTemplate.getForEntity("http://user-service:9001/user/{id}/{pass}",
                    UserCreateResponse.class, authRequest.getId(), authRequest.getPassword());
            UserCreateResponse user = Optional.ofNullable(response.getBody()).orElseThrow();
            String accessToken = jwt.generate(user, "ACCESS");
            String refreshToken = jwt.generate(user, "REFRESH");
            return new AuthResponse(accessToken, refreshToken);
        } catch (Exception e) {
            throw new UnauthorizedException("Login or password is failed", e);
        }
    }

}
