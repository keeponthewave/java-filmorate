package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private long id;

    @Email(groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class})
    private String email;

    @Pattern(regexp = "^\\S+$", groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class, Update.class})
    private String login;

    private String name;

    @PastOrPresent(groups = {Create.class, Update.class})
    private LocalDate birthday;

    public interface Create {

    }

    public interface Update {

    }
}