package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.CrudService;

@RestController()
@RequestMapping("films")
public class FilmController extends CrudController<Film> {
    public FilmController(CrudService<Film> mainService) {
        super(mainService);
    }
}
