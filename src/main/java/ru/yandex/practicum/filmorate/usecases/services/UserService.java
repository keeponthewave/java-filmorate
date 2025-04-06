package ru.yandex.practicum.filmorate.usecases.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.entities.User;
import ru.yandex.practicum.filmorate.usecases.abstractions.CrudService;
import ru.yandex.practicum.filmorate.usecases.abstractions.Repository;
import ru.yandex.practicum.filmorate.usecases.exceptions.AlreadyFriendsException;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class UserService extends CrudService<User> {
    public UserService(Repository<User> repository) {
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
    public User updateOne(User user) {
        var oldUser = repository.get(user.getId());
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            user.setEmail(oldUser.getEmail());
        }
        user.setFriendIds(oldUser.getFriendIds());
        return super.updateOne(user);
    }

    public void addToFriends(long userId, long friendId) {
        var user = repository.get(userId);
        var friend = repository.get(friendId);

        if (user.hasFriend(friendId) || friend.hasFriend(userId)) {
            throw new AlreadyFriendsException(user.getId(), friend.getId());
        }

        user.makeFriendsById(friendId);
        friend.makeFriendsById(userId);

        repository.update(user);
        repository.update(friend);
    }

    public void deleteFromFriends(long userId, long friendId) {
        var user = repository.get(userId);
        var friend = repository.get(friendId);

        if (!(user.hasFriend(friendId) && friend.hasFriend(userId))) {
            return;
        }

        user.stopBeingFriendsById(friendId);
        friend.stopBeingFriendsById(userId);

        repository.update(user);
        repository.update(friend);
    }

    public List<User> getFriendsByUserId(long id) {
        return repository
                .get(id)
                .getFriendIds()
                .stream()
                .map(repository::get)
                .toList();
    }

    public List<User> getCommonFriends(long firstUserId, long secondUserId) {
        var firstUser = repository.get(firstUserId);
        var secondUser = repository.get(secondUserId);

        var commonFriendIds = new HashSet<>(firstUser.getFriendIds());
        commonFriendIds.retainAll(secondUser.getFriendIds());

        return commonFriendIds
                .stream()
                .map(repository::get)
                .toList();
    }
}
