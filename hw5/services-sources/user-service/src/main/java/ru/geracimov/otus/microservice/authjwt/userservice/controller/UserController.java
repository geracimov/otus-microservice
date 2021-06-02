package ru.geracimov.otus.microservice.authjwt.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import ru.geracimov.otus.microservice.authjwt.userservice.domain.User;
import ru.geracimov.otus.microservice.authjwt.userservice.dto.UserCreateRequest;
import ru.geracimov.otus.microservice.authjwt.userservice.dto.UserResponse;
import ru.geracimov.otus.microservice.authjwt.userservice.exception.AccessDeniedException;
import ru.geracimov.otus.microservice.authjwt.userservice.service.UserService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable ObjectId id, @RequestHeader("X-subject") String subject) {
        if (!id.toString().equals(subject)) {
            throw new AccessDeniedException("Access denied");
        }
        return UserResponse.of(userService.getUserById(id));
    }

    @GetMapping("/{id}/{plainPass}")
    public UserResponse getAllUsers(@PathVariable ObjectId id, @PathVariable String plainPass) {
        return UserResponse.of(userService.getUserById(id, plainPass));
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        var user = User.builder()
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                .build();
        var saved = userService.saveUser(user);
        return UserResponse.of(saved);
    }


}
