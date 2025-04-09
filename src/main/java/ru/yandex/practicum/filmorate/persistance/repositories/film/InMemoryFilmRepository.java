package ru.yandex.practicum.filmorate.persistance.repositories.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.application.repositories.FilmRepository;
import ru.yandex.practicum.filmorate.entities.Film;
import ru.yandex.practicum.filmorate.persistance.abstractions.AbstractInMemoryRepository;

import java.util.Comparator;
import java.util.List;

@Repository
public class InMemoryFilmRepository extends AbstractInMemoryRepository<Film> implements FilmRepository {
    @Override
    public String notFoundMessageFactory(Long id) {
        return String.format("Фильм с id=%d не найден.", id);
    }

    @Override
    public List<Film> getPopular(int count) {
        return storageMap.values()
                .stream()
                .sorted(Comparator.<Film, Integer>comparing(f -> f.getLikedUIds().size()).reversed())
                .limit(count)
                .toList();
    }
}
