package ru.yandex.practicum.filmorate.usecases.exceptions;

import lombok.Getter;

@Getter
public class FriendNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Пользователи с id=%d,%d - не друзья";
    private final long[] friendIds = new long[2];

    public FriendNotFoundException(long userId, long friendId) {
        super(String.format(MESSAGE_TEMPLATE, userId, friendId));
        this.friendIds[0] = userId;
        this.friendIds[1] = friendId;
    }
}