package ru.geracimov.otus.microservice.authjwt.userservice.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.microservice.authjwt.userservice.domain.User;
import ru.geracimov.otus.microservice.authjwt.userservice.exception.UserNotFoundException;
import ru.geracimov.otus.microservice.authjwt.userservice.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(ObjectId id, String plainPass) {
        Optional<User> userById = userRepository.getUserById(id);
        if (userById.isEmpty() || !passwordIsCorrect(plainPass, userById.get()))
            throw new UserNotFoundException("user not found: " + id);
        return userById.get();
    }

    private boolean passwordIsCorrect(String plainPass, User user) {
        return BCrypt.checkpw(plainPass, user.getPassword());
    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        String encoded = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encoded);
        return userRepository.save(user);
    }
}
