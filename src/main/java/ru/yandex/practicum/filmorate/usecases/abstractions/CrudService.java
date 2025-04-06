package ru.yandex.practicum.filmorate.usecases.abstractions;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.entities.Entity;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudService<T extends Entity> {
    protected final Repository<T> repository;

    public List<T> getAll() {
        return repository.getAll();
    }

    public T getOne(long id) {
        return repository.get(id);
    }

    public T createOne(T postDto) {
        return repository.create(postDto);
    }

    public T updateOne(T putDto) {
        return repository.update(putDto);
    }

    public T deleteOne(long id) {
        return repository.delete(id);
    }
}
