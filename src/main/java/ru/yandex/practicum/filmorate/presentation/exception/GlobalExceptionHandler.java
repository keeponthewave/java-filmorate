package ru.yandex.practicum.filmorate.presentation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.application.exceptions.*;
import ru.yandex.practicum.filmorate.persistance.exceptions.NotFoundInStorageException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundInStorageException.class, LikeNotFoundException.class, FriendNotFoundException.class})
    public ApiError handleNotFound(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ApiError("Ресурс не найден", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiError handleValidationExceptions(
            MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ApiError("Ошибка валидации", "Неверные параметры запроса", errors);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {AlreadyLikedException.class, AlreadyFriendsException.class})
    public ApiError handleConflict(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ApiError("Конфликт", e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler()
    public ApiError handleConflict(DuplicateEmailException e) {
        log.error("{}. {}", e.getMessage(), e.getConflictUser(), e);
        return new ApiError("Конфликт", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiError handleNotFoundInStorageException(Throwable e) {
        log.error(e.getMessage(), e);
        return new ApiError("Непредвиденная ошибка", "Произошла непредвиденная ошибка");
    }
}
