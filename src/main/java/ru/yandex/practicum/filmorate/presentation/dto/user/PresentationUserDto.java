package ru.yandex.practicum.filmorate.presentation.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.presentation.validation.ValidationGroups;

import java.time.LocalDate;

@Data
public class PresentationUserDto {
    @NotNull(groups = {ValidationGroups.Update.class})
    private long id;

    @Email(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    @NotBlank(groups = {ValidationGroups.Create.class})
    private String email;

    @Pattern(regexp = "^\\S+$", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    @NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String login;

    private String name;

    @PastOrPresent(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private LocalDate birthday;
}
