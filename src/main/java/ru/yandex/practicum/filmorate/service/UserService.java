package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.abstraction.Storage;

@Service
public class UserService extends CrudService<User>{
    public UserService(Storage<User> repository) {
        super(repository);
    }

    @Override
    public User createOne(User userDto) {
        if (userDto.getName() == null || userDto.getName().isBlank()) {
            userDto.setName(userDto.getLogin());
        }
        return super.createOne(userDto);
    }

    @Override
    public User updateOne(User userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            userDto.setEmail(repository.get(userDto.getId()).getEmail());
        }
        return super.updateOne(userDto);
    }
}
