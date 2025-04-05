package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModel {
    @NotNull(groups = { Update.class})
    protected long id;

    public interface Create {

    }

    public interface Update {

    }
}
