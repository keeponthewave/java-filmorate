package ru.yandex.practicum.filmorate.gateways.dto.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yandex.practicum.filmorate.gateways.abstractions.DtoMapper;
import ru.yandex.practicum.filmorate.entities.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends DtoMapper<UserDto, User> {

}
