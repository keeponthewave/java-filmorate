package ru.yandex.practicum.filmorate.gateways.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.gateways.abstractions.CrudController;
import ru.yandex.practicum.filmorate.gateways.dto.user.UserDto;
import ru.yandex.practicum.filmorate.gateways.dto.user.UserMapper;
import ru.yandex.practicum.filmorate.entities.User;
import ru.yandex.practicum.filmorate.usecases.services.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("users")
public class UserController extends CrudController<User, UserDto, UserService> {

    private final UserMapper userMapper;

    public UserController(UserService mainService, UserMapper dtoMapper,
                          UserMapper userMapper) {
        super(mainService, dtoMapper);
        this.userMapper = userMapper;
    }

    @PutMapping("{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToFriends(@PathVariable long id, @PathVariable long friendId) {
        mainService.addToFriends(id, friendId);
    }

    @DeleteMapping("{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromFriends(@PathVariable long id, @PathVariable long friendId) {
        mainService.deleteFromFriends(id, friendId);
    }

    @GetMapping("{id}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getFriends(@PathVariable long id) {
        return mainService.getFriendsByUserId(id).stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("{id}/friends/common/{otherId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getCommonFriends(@PathVariable long id, @PathVariable long otherId) {
        return mainService.getCommonFriends(id, otherId).stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
