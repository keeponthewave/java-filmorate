package ru.yandex.practicum.filmorate.persistance.repositories.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.entities.User;
import ru.yandex.practicum.filmorate.persistance.abstractions.AbstractInMemoryRepository;

@Repository
public class InMemoryUserRepository extends AbstractInMemoryRepository<User> {
    @Override
    public String notFoundMessageFactory(long id) {
        return String.format("Пользователь с id=%d не найден.", id);
    }
}
