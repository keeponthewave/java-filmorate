package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
    private final Map<Long, User> usersMap = new HashMap<>();
    private long idCounter = 1L;

    @PostMapping
    public ResponseEntity<?> createOne(@Valid @RequestBody User user) {
        user.setId(idCounter++);
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        usersMap.put(user.getId(), user);
        log.info("POST /users requestBody={} 200 OK", user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateOne(@Valid @RequestBody User user) {
        if (!usersMap.containsKey(user.getId())) {
            log.error("PUT /users requestBody={} 404 NOT FOUND", user);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        usersMap.put(user.getId(), user);
        log.info("PUT /users requestBody={} 200 OK", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ArrayList<>(usersMap.values()), HttpStatus.OK);
    }
}
