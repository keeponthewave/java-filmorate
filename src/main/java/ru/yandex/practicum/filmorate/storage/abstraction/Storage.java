package ru.yandex.practicum.filmorate.storage.abstraction;

import ru.yandex.practicum.filmorate.model.BaseModel;

import java.util.List;

public interface Storage<T extends BaseModel> {
    default String notFoundMessageFactory(long id) {
        return String.format("Сущность с id=%d не найдена", id);
    }
    List<T> getAll();
    T get(long id);
    T create(T postDto);
    T update(T putDto);
    T delete(long id);
}
