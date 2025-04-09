package ru.yandex.practicum.filmorate.persistance.abstractions;

import ru.yandex.practicum.filmorate.entities.Entity;
import ru.yandex.practicum.filmorate.persistance.exceptions.NotFoundInStorageException;
import ru.yandex.practicum.filmorate.application.repositories.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractInMemoryRepository<T extends Entity> implements Repository<T, Long> {
    protected final Map<Long, T> storageMap = new HashMap<>();

    public T create(T postDto) {
        var id = getNextId();
        postDto.setId(id);
        storageMap.put(id, postDto);
        return postDto;
    }

    @Override
    public T get(Long id) {
        if (!storageMap.containsKey(id)) {
            throw new NotFoundInStorageException(notFoundMessageFactory(id), id);
        }
        return storageMap.get(id);
    }

    @Override
    public T update(T putDto) {
        var id = putDto.getId();
        if (!storageMap.containsKey(id)) {
            throw new NotFoundInStorageException(notFoundMessageFactory(id), id);
        }
        storageMap.put(id, putDto);
        return putDto;
    }

    @Override
    public T delete(Long id) {
        if (!storageMap.containsKey(id)) {
            throw new NotFoundInStorageException(notFoundMessageFactory(id), id);
        }
        var entity = storageMap.get(id);
        storageMap.remove(id);
        return entity;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public boolean has(Long id) {
        return storageMap.containsKey(id);
    }

    protected long getNextId() {
        long currentMaxId = storageMap.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
