package ru.yandex.practicum.filmorate.usecases.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class AlreadyFriendsException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Пользователи с id=%d,%d - уже друзья";
    private final long[] friendIds = new long[2];

    public AlreadyFriendsException(long userId, long friendId) {
        super(String.format(MESSAGE_TEMPLATE, userId, friendId));
        this.friendIds[0] = userId;
        this.friendIds[1] = friendId;
    }
}
