package ru.yandex.practicum.filmorate.gateways.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.gateways.validation.ValidationGroups;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotNull(groups = {ValidationGroups.Update.class})
    private long id;

    @NotNull(groups = {ValidationGroups.Update.class})
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
