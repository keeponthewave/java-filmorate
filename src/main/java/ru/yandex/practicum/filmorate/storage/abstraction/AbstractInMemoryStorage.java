package ru.yandex.practicum.filmorate.storage.abstraction;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.BaseModel;
import ru.yandex.practicum.filmorate.storage.exception.NotFoundInStorageException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractInMemoryStorage<T extends BaseModel> implements Storage<T> {
    private final Map<Long, T> storageMap = new HashMap<>();

    public T create(T postDto) {
        var id = getNextId();
        postDto.setId(id);
        storageMap.put(id, postDto);
        return postDto;
    }

    @Override
    public T get(long id) {
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
    public T delete(long id) {
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

    protected long getNextId() {
        long currentMaxId = storageMap.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
