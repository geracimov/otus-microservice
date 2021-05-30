package ru.geracimov.otus.microservice.authjwt.userservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.geracimov.otus.microservice.authjwt.userservice.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> getUserById(ObjectId id);
}
