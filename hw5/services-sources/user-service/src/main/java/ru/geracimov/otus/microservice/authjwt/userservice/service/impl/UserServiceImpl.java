package ru.geracimov.otus.microservice.authjwt.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.microservice.authjwt.userservice.domain.User;
import ru.geracimov.otus.microservice.authjwt.userservice.exception.UserNotFoundException;
import ru.geracimov.otus.microservice.authjwt.userservice.repository.UserRepository;
import ru.geracimov.otus.microservice.authjwt.userservice.service.UserService;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(ObjectId id, String plainPass) {
        Optional<User> userById = userRepository.getUserById(id);
        if (userById.isEmpty() || !passwordIsCorrect(plainPass, userById.get()))
            throw new UserNotFoundException("user not found: " + id);
        return userById.get();
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        String encoded = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encoded);
        return userRepository.save(user);
    }

    private boolean passwordIsCorrect(String plainPass, User user) {
        return BCrypt.checkpw(plainPass, user.getPassword());
    }

}
