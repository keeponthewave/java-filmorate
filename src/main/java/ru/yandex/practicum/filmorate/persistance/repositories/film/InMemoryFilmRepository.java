package ru.yandex.practicum.filmorate.persistance.repositories.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.entities.Film;
import ru.yandex.practicum.filmorate.persistance.abstractions.AbstractInMemoryRepository;

@Repository
public class InMemoryFilmRepository extends AbstractInMemoryRepository<Film> {
    @Override
    public String notFoundMessageFactory(long id) {
        return String.format("Фильм с id=%d не найден.", id);
    }
}
