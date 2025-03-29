package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private long id;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^\\S+$")
    @NotBlank
    private String login;

    private String name;

    @PastOrPresent
    private LocalDate birthday;
}