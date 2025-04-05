package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.controller.validation.IsPresentOrAfter;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
public class Film extends BaseModel {
    @NotBlank(groups = {BaseModel.Create.class, BaseModel.Update.class})
    private String name;

    @Size(max = 200, message = "Превышена максимальная длина описания фильма — 200 символов",
            groups = {BaseModel.Create.class, BaseModel.Update.class})
    private String description;

    @IsPresentOrAfter(value = "1895-12-28", message = "Дата релиза должна быть не раньше 28 декабря 1895",
            groups = {BaseModel.Create.class, BaseModel.Update.class})
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть положительным числом",
            groups = {BaseModel.Create.class, BaseModel.Update.class})
    private int duration;
}