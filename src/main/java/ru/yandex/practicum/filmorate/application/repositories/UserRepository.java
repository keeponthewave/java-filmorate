package ru.yandex.practicum.filmorate.application.repositories;

import ru.yandex.practicum.filmorate.entities.User;

public interface UserRepository extends Repository<User, Long> {
    User getByEmail(String email);
}
