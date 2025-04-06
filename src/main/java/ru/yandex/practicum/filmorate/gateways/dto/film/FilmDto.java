package ru.yandex.practicum.filmorate.gateways.dto.film;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.gateways.validation.IsPresentOrAfter;
import ru.yandex.practicum.filmorate.gateways.validation.ValidationGroups;

import java.time.LocalDate;

@Data
public class FilmDto {
    @NotNull(groups = {ValidationGroups.Update.class})
    private long id;

    @NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @Size(max = 200, message = "Превышена максимальная длина описания фильма — 200 символов",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String description;

    @IsPresentOrAfter(value = "1895-12-28", message = "Дата релиза должна быть не раньше 28 декабря 1895",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть положительным числом",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private int duration;
}
