package ru.yandex.practicum.filmorate.usecases.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class LikeNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Фильм с id=%d не имеет оценки от пользователя с id=%d";
    private final long userId;
    private final long filmId;

    public LikeNotFoundException(long filmId, long userId) {
        super(String.format(MESSAGE_TEMPLATE, filmId, userId));
        this.filmId = filmId;
        this.userId = userId;
    }
}
