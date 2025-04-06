package ru.yandex.practicum.filmorate.usecases.abstractions;

import ru.yandex.practicum.filmorate.entities.Entity;

import java.util.List;

public interface Repository<T extends Entity> {
    default String notFoundMessageFactory(long id) {
        return String.format("Сущность с id=%d не найдена", id);
    }

    List<T> getAll();

    T get(long id);

    T create(T postDto);

    T update(T putDto);

    T delete(long id);

    boolean has(long id);
}
