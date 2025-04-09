package ru.yandex.practicum.filmorate.presentation.dto.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yandex.practicum.filmorate.application.dto.user.AppUserDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PresentationUserDtoMapper {
    AppUserDto toAppDto(PresentationUserDto dto);

    PresentationUserDto fromAppDto(AppUserDto dto);
}
