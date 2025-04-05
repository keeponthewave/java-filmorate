package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.abstraction.Storage;

@Service
public class FilmService extends CrudService<Film>{
    public FilmService(Storage<Film> repository) {
        super(repository);
    }
}
