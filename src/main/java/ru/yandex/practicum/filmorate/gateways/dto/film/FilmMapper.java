package ru.yandex.practicum.filmorate.gateways.dto.film;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yandex.practicum.filmorate.gateways.abstractions.DtoMapper;
import ru.yandex.practicum.filmorate.entities.Film;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilmMapper extends DtoMapper<FilmDto, Film> {

}
