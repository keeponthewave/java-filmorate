package ru.yandex.practicum.filmorate.application.dto.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yandex.practicum.filmorate.entities.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppUserDtoMapper {
    User toEntity(AppUserDto dto);

    AppUserDto fromEntity(User dto);
}
