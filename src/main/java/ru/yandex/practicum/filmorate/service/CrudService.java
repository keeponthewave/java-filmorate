package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.BaseModel;
import ru.yandex.practicum.filmorate.storage.abstraction.Storage;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudService<T extends BaseModel> {
    protected final Storage<T> repository;

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
