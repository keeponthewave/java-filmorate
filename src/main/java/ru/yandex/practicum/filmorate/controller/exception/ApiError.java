package ru.yandex.practicum.filmorate.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiError {
    private final String title;
    private final String detail;
    private Map<String, String> errors = new HashMap<>();
}
