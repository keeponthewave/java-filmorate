package ru.yandex.practicum.filmorate.application.dto.film;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yandex.practicum.filmorate.entities.Film;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppFilmDtoMapper {
    Film toEntity(AppFilmDto dto);

    AppFilmDto fromEntity(Film dto);
}
