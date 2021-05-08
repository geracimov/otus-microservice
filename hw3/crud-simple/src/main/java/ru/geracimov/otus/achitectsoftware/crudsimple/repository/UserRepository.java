package ru.geracimov.otus.achitectsoftware.crudsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.achitectsoftware.crudsimple.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
