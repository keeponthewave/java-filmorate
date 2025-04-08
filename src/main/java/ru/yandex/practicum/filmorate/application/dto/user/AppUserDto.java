package ru.yandex.practicum.filmorate.application.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppUserDto {
    private long id;

    private String email;

    private String login;

    private String name;

    private LocalDate birthday;
}
