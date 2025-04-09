package ru.yandex.practicum.filmorate.application.services.interfaces;

import ru.yandex.practicum.filmorate.application.dto.user.AppUserDto;

import java.util.List;

public interface UserService extends CrudService<AppUserDto, Long> {
    void addToFriends(long userId, long friendId);

    void deleteFromFriends(long userId, long friendId);

    List<AppUserDto> getFriendsByUserId(long id);

    List<AppUserDto> getCommonFriends(long firstUserId, long secondUserId);
}
