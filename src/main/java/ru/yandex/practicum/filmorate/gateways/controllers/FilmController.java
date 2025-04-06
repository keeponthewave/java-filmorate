package ru.yandex.practicum.filmorate.gateways.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.gateways.abstractions.CrudController;
import ru.yandex.practicum.filmorate.gateways.dto.film.FilmDto;
import ru.yandex.practicum.filmorate.gateways.dto.film.FilmMapper;
import ru.yandex.practicum.filmorate.entities.Film;
import ru.yandex.practicum.filmorate.usecases.services.FilmService;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("films")
public class FilmController extends CrudController<Film, FilmDto, FilmService> {

    private final FilmMapper filmMapper;

    public FilmController(FilmService mainService, FilmMapper dtoMapper,
                          FilmMapper filmMapper) {
        super(mainService, dtoMapper);
        this.filmMapper = filmMapper;
    }

    @PutMapping("{id}/like/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToFriends(@PathVariable long id, @PathVariable long userId) {
        mainService.addLike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromFriends(@PathVariable long id, @PathVariable long userId) {
        mainService.deleteLike(id, userId);
    }

    @GetMapping("popular")
    @ResponseStatus(HttpStatus.OK)
    public List<FilmDto> getFriends(@RequestParam(required = false, defaultValue = "10") int count) {
        return mainService.getPopular(count)
                .stream()
                .map(filmMapper::toDto)
                .collect(Collectors.toList());
    }
}
