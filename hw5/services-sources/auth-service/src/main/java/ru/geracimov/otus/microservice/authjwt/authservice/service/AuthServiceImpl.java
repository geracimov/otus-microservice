package ru.geracimov.otus.microservice.authjwt.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.AuthResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserCreateRequest;
import ru.geracimov.otus.microservice.authjwt.authservice.dto.UserResponse;
import ru.geracimov.otus.microservice.authjwt.authservice.exception.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final JwtUtil jwt;

    @Override
    public UserResponse register(UserCreateRequest userCreateRequest) {
        return restTemplate.postForObject("http://user-service:9001/user", userCreateRequest, UserResponse.class);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        ResponseEntity<UserResponse> response = restTemplate.getForEntity("http://user-service:9001/user/{id}/{pass}",
                UserResponse.class, authRequest.getId(), authRequest.getPassword());
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new UnauthorizedException("Login or password is failed");
        }
        UserResponse user = response.getBody();
        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }
}
