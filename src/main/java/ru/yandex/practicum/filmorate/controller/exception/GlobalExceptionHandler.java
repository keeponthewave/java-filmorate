package ru.yandex.practicum.filmorate.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.storage.exception.NotFoundInStorageException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ApiError handleNotFoundInStorageException(NotFoundInStorageException e) {
        log.error(e.getMessage(), e);
        return new ApiError("Ресурс не найден", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiError handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ApiError("Ошибка валидации", "Неверные параметры запроса", errors);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiError handleNotFoundInStorageException(Throwable e) {
        log.error(e.getMessage(), e);
        return new ApiError("Непредвиденная ошибка", "Произошла непредвиденная ошибка");
    }
}
