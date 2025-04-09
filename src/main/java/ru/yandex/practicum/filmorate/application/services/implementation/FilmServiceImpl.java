package ru.yandex.practicum.filmorate.application.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.application.dto.film.AppFilmDto;
import ru.yandex.practicum.filmorate.application.dto.film.AppFilmDtoMapper;
import ru.yandex.practicum.filmorate.application.repositories.FilmRepository;
import ru.yandex.practicum.filmorate.application.repositories.UserRepository;
import ru.yandex.practicum.filmorate.application.services.interfaces.FilmService;
import ru.yandex.practicum.filmorate.persistance.exceptions.NotFoundInStorageException;
import ru.yandex.practicum.filmorate.application.exceptions.AlreadyLikedException;
import ru.yandex.practicum.filmorate.application.exceptions.LikeNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final AppFilmDtoMapper filmDtoMapper;

    @Override
    public List<AppFilmDto> getAll() {
        return filmRepository.getAll().stream().map(filmDtoMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AppFilmDto getOne(Long id) {
        return filmDtoMapper.fromEntity(filmRepository.get(id));
    }

    @Override
    public AppFilmDto createOne(AppFilmDto postDto) {
        return filmDtoMapper.fromEntity(filmRepository.create(filmDtoMapper.toEntity(postDto)));
    }

    @Override
    public AppFilmDto updateOne(AppFilmDto putDto) {
        return filmDtoMapper.fromEntity(filmRepository.update(filmDtoMapper.toEntity(putDto)));
    }

    @Override
    public AppFilmDto deleteOne(Long id) {
        return filmDtoMapper.fromEntity(filmRepository.delete(id));
    }

    @Override
    public void addLike(long filmId, long userId) {
        if (!userRepository.has(userId)) {
            throw new NotFoundInStorageException(userRepository.notFoundMessageFactory(userId), userId);
        }

        var film = filmRepository.get(filmId);

        if (film.isLikedBy(userId)) {
            throw new AlreadyLikedException(userId, filmId);
        }

        film.likeBy(userId);
        filmRepository.update(film);
    }

    @Override
    public void deleteLike(long filmId, long userId) {
        if (!userRepository.has(userId)) {
            throw new NotFoundInStorageException(userRepository.notFoundMessageFactory(userId), userId);
        }

        var film = filmRepository.get(filmId);

        if (!film.isLikedBy(userId)) {
            throw new LikeNotFoundException(filmId, userId);
        }

        film.removeLikeBy(userId);
        filmRepository.update(film);
    }

    @Override
    public List<AppFilmDto> getPopular(int count) {
        return filmRepository
                .getPopular(count)
                .stream()
                .map(filmDtoMapper::fromEntity)
                .collect(Collectors.toList());

    }
}
