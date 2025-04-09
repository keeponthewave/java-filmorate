package ru.yandex.practicum.filmorate.persistance.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class NotFoundInStorageException extends RuntimeException {
    private final Long id;

    public NotFoundInStorageException(String message, long id) {
        super(message);
        this.id = id;
    }
}
