package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.CrudService;


@Slf4j
@RestController
@RequestMapping("users")
public class UserController extends CrudController<User> {
    public UserController(CrudService<User> mainService) {
        super(mainService);
    }
}
