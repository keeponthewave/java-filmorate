package ru.yandex.practicum.filmorate.application.repositories;

import ru.yandex.practicum.filmorate.entities.Film;

import java.util.List;

public interface FilmRepository extends Repository<Film, Long> {
    List<Film> getPopular(int count);
}
