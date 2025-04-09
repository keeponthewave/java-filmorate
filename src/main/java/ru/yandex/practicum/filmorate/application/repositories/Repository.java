package ru.yandex.practicum.filmorate.application.repositories;

import ru.yandex.practicum.filmorate.entities.Entity;

import java.util.List;

public interface Repository<T extends Entity, K> {
    default String notFoundMessageFactory(K id) {
        return String.format("Сущность с id=%s не найдена", id);
    }

    List<T> getAll();

    T get(K id);

    T create(T postDto);

    T update(T putDto);

    T delete(K id);

    boolean has(K id);
}
