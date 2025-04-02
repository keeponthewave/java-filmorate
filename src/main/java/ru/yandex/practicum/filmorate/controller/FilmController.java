package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("films")
public class FilmController {
    private final Map<Long, Film> filmsMap = new HashMap<>();
    private long idCounter = 1L;

    @PostMapping
    public ResponseEntity<?> createOne(@Valid @RequestBody Film film) {
        film.setId(idCounter++);
        filmsMap.put(film.getId(), film);
        log.info("POST /films requestBody={} 200 OK", film);
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateOne(@Valid @RequestBody Film film) {
        if (!filmsMap.containsKey(film.getId())) {
            log.error("PUT /films requestBody={} 404 NOT FOUND", film);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }
        filmsMap.put(film.getId(), film);
        log.info("PUT /films requestBody={} 200 OK", film);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ArrayList<>(filmsMap.values()), HttpStatus.OK);
    }
}
