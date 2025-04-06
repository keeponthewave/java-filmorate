package ru.yandex.practicum.filmorate.gateways.abstractions;

public interface DtoMapper<DtoT, ModelT> {
    DtoT toDto(ModelT model);

    ModelT fromDto(DtoT dto);
}
