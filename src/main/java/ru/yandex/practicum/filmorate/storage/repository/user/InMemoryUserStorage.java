package ru.yandex.practicum.filmorate.storage.repository.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.abstraction.AbstractInMemoryStorage;

@Repository
public class InMemoryUserStorage extends AbstractInMemoryStorage<User> {
    @Override
    public String notFoundMessageFactory(long id) {
        return String.format("Пользователь с id=%d не найден.", id);
    }
}
