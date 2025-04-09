package ru.yandex.practicum.filmorate.presentation.dto.film;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yandex.practicum.filmorate.application.dto.film.AppFilmDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PresentationFilmDtoMapper {
    AppFilmDto toAppDto(PresentationFilmDto dto);

    PresentationFilmDto fromAppDto(AppFilmDto dto);
}
