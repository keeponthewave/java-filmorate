package ru.yandex.practicum.filmorate.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.application.services.interfaces.FilmService;
import ru.yandex.practicum.filmorate.presentation.dto.film.PresentationFilmDto;
import ru.yandex.practicum.filmorate.presentation.dto.film.PresentationFilmDtoMapper;
import ru.yandex.practicum.filmorate.presentation.validation.ValidationGroups;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("films")
@RequiredArgsConstructor
public class FilmController {

    private final PresentationFilmDtoMapper filmMapper;
    private final FilmService filmService;
    private final PresentationFilmDtoMapper presentationFilmDtoMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    protected List<PresentationFilmDto> getAll() {
        return filmService.getAll().stream().map(presentationFilmDtoMapper::fromAppDto).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    protected PresentationFilmDto getOne(@PathVariable long id) {
        return filmMapper.fromAppDto(filmService.getOne(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    protected PresentationFilmDto createOne(@Validated(ValidationGroups.Create.class) @RequestBody PresentationFilmDto dto) {
        return filmMapper.fromAppDto(filmService.createOne(filmMapper.toAppDto(dto)));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    protected PresentationFilmDto updateOne(@Validated(ValidationGroups.Update.class) @RequestBody PresentationFilmDto dto) {
        return filmMapper.fromAppDto(filmService.updateOne(filmMapper.toAppDto(dto)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    protected PresentationFilmDto deleteOne(@PathVariable long id) {
        return filmMapper.fromAppDto(filmService.deleteOne(id));
    }

    @PutMapping("{id}/like/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToFriends(@PathVariable long id, @PathVariable long userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromFriends(@PathVariable long id, @PathVariable long userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("popular")
    @ResponseStatus(HttpStatus.OK)
    public List<PresentationFilmDto> getFriends(@RequestParam(required = false, defaultValue = "10") int count) {
        return filmService.getPopular(count)
                .stream()
                .map(filmMapper::fromAppDto)
                .collect(Collectors.toList());
    }
}
