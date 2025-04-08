package ru.yandex.practicum.filmorate.persistance.repositories.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.application.repositories.UserRepository;
import ru.yandex.practicum.filmorate.entities.User;
import ru.yandex.practicum.filmorate.persistance.abstractions.AbstractInMemoryRepository;

@Repository
public class InMemoryUserRepository extends AbstractInMemoryRepository<User> implements UserRepository {
    @Override
    public String notFoundMessageFactory(Long id) {
        return String.format("Пользователь с id=%d не найден.", id);
    }

    @Override
    public User getByEmail(String email) {
        return storageMap.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
