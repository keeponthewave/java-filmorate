package ru.yandex.practicum.filmorate.application.dto.film;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppFilmDto {
    private long id;

    private String name;

    private String description;

    private LocalDate releaseDate;

    private int duration;
}
