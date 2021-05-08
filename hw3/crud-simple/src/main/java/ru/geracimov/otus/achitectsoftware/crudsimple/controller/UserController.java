package ru.geracimov.otus.achitectsoftware.crudsimple.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geracimov.otus.achitectsoftware.crudsimple.domain.User;
import ru.geracimov.otus.achitectsoftware.crudsimple.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final Random random = new Random();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ok(userRepository.save(user));
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        if (Boolean.TRUE.equals(random.ints(1, 10).mapToObj(in -> in > 8).findAny().get())) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
        return ok(userRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@RequestBody User user, @PathVariable Long id) {
        User body = getUserById(id).getBody();
        body.setEmail(user.getEmail());
        body.setFirstName(user.getFirstName());
        body.setLastName(user.getLastName());
        body.setPhone(user.getPhone());
        body.setUsername(user.getUsername());
        return ok(userRepository.save(body));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@RequestBody User user, @PathVariable Long id) {
        User body = getUserById(id).getBody();
        body.setEmail(user.getEmail() == null ? body.getEmail() : user.getEmail());
        body.setFirstName(user.getFirstName() == null ? body.getFirstName() : user.getFirstName());
        body.setLastName(user.getLastName() == null ? body.getLastName() : user.getLastName());
        body.setPhone(user.getPhone() == null ? body.getPhone() : user.getPhone());
        body.setUsername(user.getUsername() == null ? body.getUsername() : user.getUsername());
        return ok(userRepository.save(body));
    }

    @GetMapping("/{id}")
    public @NonNull
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return ok(byId.get());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}
