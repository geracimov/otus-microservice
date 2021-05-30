package ru.geracimov.otus.microservice.authjwt.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
