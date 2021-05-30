package ru.geracimov.otus.microservice.authjwt.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
