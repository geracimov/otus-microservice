package ru.geracimov.otus.microservice.authjwt.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

}
