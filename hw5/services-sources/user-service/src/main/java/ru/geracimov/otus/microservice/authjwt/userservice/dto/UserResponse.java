package ru.geracimov.otus.microservice.authjwt.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geracimov.otus.microservice.authjwt.userservice.domain.User;

@Data
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserResponse of(User user) {
        return new UserResponse(user.getId().toString(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
