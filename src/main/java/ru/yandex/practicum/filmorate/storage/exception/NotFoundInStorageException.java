package ru.yandex.practicum.filmorate.storage.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class NotFoundInStorageException extends RuntimeException {
    private final long id;

    public NotFoundInStorageException(String message, long id) {
        super(message);
        this.id = id;
    }
}
