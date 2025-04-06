package ru.yandex.practicum.filmorate.usecases.services;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.entities.Film;
import ru.yandex.practicum.filmorate.entities.User;
import ru.yandex.practicum.filmorate.persistance.exceptions.NotFoundInStorageException;
import ru.yandex.practicum.filmorate.usecases.abstractions.CrudService;
import ru.yandex.practicum.filmorate.usecases.abstractions.Repository;
import ru.yandex.practicum.filmorate.usecases.exceptions.AlreadyLikedException;
import ru.yandex.practicum.filmorate.usecases.exceptions.LikeNotFoundException;

import java.util.Comparator;
import java.util.List;

@Service
public class FilmService extends CrudService<Film> {
    private final Repository<User> userRepository;

    public FilmService(Repository<Film> repository, Repository<User> userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    public void addLike(long filmId, long userId) {
        if (!userRepository.has(userId)) {
            throw new NotFoundInStorageException(userRepository.notFoundMessageFactory(userId), userId);
        }

        var film = repository.get(filmId);

        if (film.isLikedBy(userId)) {
            throw new AlreadyLikedException(userId, filmId);
        }

        film.likeBy(userId);
        repository.update(film);
    }

    public void deleteLike(long filmId, long userId) {
        if (!userRepository.has(userId)) {
            throw new NotFoundInStorageException(userRepository.notFoundMessageFactory(userId), userId);
        }

        var film = repository.get(filmId);

        if (!film.isLikedBy(userId)) {
            throw new LikeNotFoundException(filmId, userId);
        }

        film.removeLikeBy(userId);
        repository.update(film);
    }

    public List<Film> getPopular(int count) {
        return repository
                .getAll()
                .stream()
                .sorted(Comparator.<Film, Integer>comparing(f -> f.getLikedUIds().size()).reversed())
                .limit(count)
                .toList();

    }
}
