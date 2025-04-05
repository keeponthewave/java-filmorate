package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel {

    @Email(groups = {BaseModel.Create.class, BaseModel.Update.class})
    @NotBlank(groups = {Create.class})
    private String email;

    @Pattern(regexp = "^\\S+$", groups = {BaseModel.Create.class, BaseModel.Update.class})
    @NotBlank(groups = {BaseModel.Create.class, Update.class})
    private String login;

    private String name;

    @PastOrPresent(groups = {BaseModel.Create.class, BaseModel.Update.class})
    private LocalDate birthday;
}