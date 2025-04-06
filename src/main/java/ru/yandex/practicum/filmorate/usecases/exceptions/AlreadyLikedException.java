package ru.yandex.practicum.filmorate.usecases.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyLikedException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Пользователь с id=%d уже оценил фильм %d";
    private final long userId;
    private final long filmId;

    public AlreadyLikedException(long userId, long filmId) {
        super(String.format(MESSAGE_TEMPLATE, userId, filmId));
        this.userId = userId;
        this.filmId = filmId;
    }
}
