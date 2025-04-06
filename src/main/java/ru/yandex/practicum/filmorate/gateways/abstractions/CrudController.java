package ru.yandex.practicum.filmorate.gateways.abstractions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.gateways.validation.ValidationGroups;
import ru.yandex.practicum.filmorate.entities.Entity;
import ru.yandex.practicum.filmorate.usecases.abstractions.CrudService;

import java.util.List;

@Slf4j
public abstract class CrudController<ModelT extends Entity, DtoT, ServiceT extends CrudService<ModelT>> {
    protected ServiceT mainService;
    protected DtoMapper<DtoT, ModelT> dtoMapper;

    public CrudController(ServiceT mainService, DtoMapper<DtoT, ModelT> dtoMapper) {
        this.mainService = mainService;
        this.dtoMapper = dtoMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    protected List<DtoT> getAll() {
        return mainService.getAll().stream().map(dtoMapper::toDto).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    protected DtoT getOne(@PathVariable long id) {
        return dtoMapper.toDto(mainService.getOne(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    protected DtoT createOne(@Validated(ValidationGroups.Create.class) @RequestBody DtoT postDtoT) {
        return dtoMapper.toDto(mainService.createOne(dtoMapper.fromDto(postDtoT)));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    protected DtoT updateOne(@Validated(ValidationGroups.Update.class) @RequestBody DtoT putDtoT) {
        return dtoMapper.toDto(mainService.updateOne(dtoMapper.fromDto(putDtoT)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    protected DtoT deleteOne(@PathVariable long id) {
        return dtoMapper.toDto(mainService.deleteOne(id));
    }
}
