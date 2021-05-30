package ru.geracimov.otus.microservice.authjwt.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCheckRequest {
    private String id;
    private String password;
}
