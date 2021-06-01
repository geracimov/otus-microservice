package ru.geracimov.otus.microservice.authjwt.userservice.service;

import org.bson.types.ObjectId;
import ru.geracimov.otus.microservice.authjwt.userservice.domain.User;

import java.util.Collection;

public interface UserService {

    User getUserById(ObjectId id);

    User getUserById(ObjectId id, String plainPass);

    Collection<User> getAllUsers();

    User saveUser(User user);



}
