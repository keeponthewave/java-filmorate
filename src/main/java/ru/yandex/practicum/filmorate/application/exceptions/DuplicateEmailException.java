package ru.yandex.practicum.filmorate.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.yandex.practicum.filmorate.application.dto.user.AppUserDto;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class DuplicateEmailException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Пользователь с email=%s - уже существует";
    private final AppUserDto conflictUser;

    public DuplicateEmailException(AppUserDto conflictUser) {
        super(String.format(MESSAGE_TEMPLATE, conflictUser.getEmail()));
        this.conflictUser = conflictUser;
    }
}
