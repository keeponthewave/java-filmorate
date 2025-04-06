package ru.yandex.practicum.filmorate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
public class Film extends Entity {
    private String name;
    private String description;
    private LocalDate releaseDate;
    private int duration;

    private Set<Long> likedUIds = new HashSet<>();

    public void likeBy(long userId) {
        likedUIds.add(userId);
    }

    public void removeLikeBy(long userId) {
        likedUIds.remove(userId);
    }

    public boolean isLikedBy(long userId) {
        return likedUIds.contains(userId);
    }
}