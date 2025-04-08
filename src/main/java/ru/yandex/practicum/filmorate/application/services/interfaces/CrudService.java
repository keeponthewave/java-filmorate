package ru.yandex.practicum.filmorate.application.services.interfaces;

import java.util.List;

public interface CrudService<T, K> {
    List<T> getAll();

    T getOne(K id);

    T createOne(T postDto);

    T updateOne(T putDto);

    T deleteOne(K id);
}
