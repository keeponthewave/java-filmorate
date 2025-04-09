package ru.yandex.practicum.filmorate.application.services.interfaces;

import ru.yandex.practicum.filmorate.application.dto.film.AppFilmDto;

import java.util.List;

public interface FilmService extends CrudService<AppFilmDto, Long> {
    void addLike(long filmId, long userId);

    void deleteLike(long filmId, long userId);

    List<AppFilmDto> getPopular(int count);
}
