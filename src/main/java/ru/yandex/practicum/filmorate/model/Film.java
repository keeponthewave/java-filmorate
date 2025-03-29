package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.IsPresentOrAfter;

import java.time.LocalDate;


@Data
public class Film {
    private long id;

    @NotBlank
    private String name;

    @Size(max = 200, message = "Превышена максимальная длина описания фильма — 200 символов")
    private String description;

    @IsPresentOrAfter(value = "1895-12-28", message = "Дата релиза должна быть не раньше 28 декабря 1895")
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть положительным числом")
    private int duration;
}