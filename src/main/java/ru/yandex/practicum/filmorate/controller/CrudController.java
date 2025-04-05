package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.BaseModel;
import ru.yandex.practicum.filmorate.service.CrudService;

import java.util.List;

@Slf4j
public class CrudController<T extends BaseModel> {
    protected CrudService<T> mainService;

    public CrudController(CrudService<T> mainService) {
        this.mainService = mainService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    protected List<T> getAll() {
        return mainService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    protected T getOne(@PathVariable long id) {
        return mainService.getOne(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    protected T createOne(@Validated(BaseModel.Create.class) @RequestBody T postDto) {
        return mainService.createOne(postDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    protected T updateOne(@Validated(BaseModel.Update.class) @RequestBody T putDto) {
        return mainService.updateOne(putDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    protected T deleteOne(@PathVariable long id) {
        return mainService.deleteOne(id);
    }
}
