package ru.yandex.practicum.filmorate.storage.repository.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.abstraction.AbstractInMemoryStorage;

@Repository
public class InMemoryFilmStorage extends AbstractInMemoryStorage<Film> {
    @Override
    public String notFoundMessageFactory(long id) {
        return String.format("Фильм с id=%d не найден.", id);
    }
}
