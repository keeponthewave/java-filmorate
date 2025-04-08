package ru.yandex.practicum.filmorate.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.application.services.interfaces.UserService;
import ru.yandex.practicum.filmorate.presentation.dto.user.PresentationUserDto;
import ru.yandex.practicum.filmorate.presentation.dto.user.PresentationUserDtoMapper;
import ru.yandex.practicum.filmorate.presentation.validation.ValidationGroups;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final PresentationUserDtoMapper userMapper;
    private final UserService userService;
    private final PresentationUserDtoMapper presentationUserDtoMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    protected List<PresentationUserDto> getAll() {
        return userService.getAll().stream().map(userMapper::fromAppDto).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    protected PresentationUserDto getOne(@PathVariable long id) {
        return userMapper.fromAppDto(userService.getOne(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    protected PresentationUserDto createOne(@Validated(ValidationGroups.Create.class) @RequestBody PresentationUserDto dto) {
        return userMapper.fromAppDto(userService.createOne(userMapper.toAppDto(dto)));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    protected PresentationUserDto updateOne(@Validated(ValidationGroups.Update.class) @RequestBody PresentationUserDto dto) {
        return userMapper.fromAppDto(userService.updateOne(userMapper.toAppDto(dto)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    protected PresentationUserDto deleteOne(@PathVariable long id) {
        return userMapper.fromAppDto(userService.deleteOne(id));
    }

    @PutMapping("{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToFriends(@PathVariable long id, @PathVariable long friendId) {
        userService.addToFriends(id, friendId);
    }

    @DeleteMapping("{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromFriends(@PathVariable long id, @PathVariable long friendId) {
        userService.deleteFromFriends(id, friendId);
    }

    @GetMapping("{id}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<PresentationUserDto> getFriends(@PathVariable long id) {
        return userService.getFriendsByUserId(id).stream().map(presentationUserDtoMapper::fromAppDto).collect(Collectors.toList());
    }

    @GetMapping("{id}/friends/common/{otherId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PresentationUserDto> getCommonFriends(@PathVariable long id, @PathVariable long otherId) {
        return userService.getCommonFriends(id, otherId).stream().map(userMapper::fromAppDto).collect(Collectors.toList());
    }
}
