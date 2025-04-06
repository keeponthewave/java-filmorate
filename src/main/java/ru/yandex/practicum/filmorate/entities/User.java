package ru.yandex.practicum.filmorate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Entity {
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
    private Set<Long> friendIds = new HashSet<>();

    public void makeFriendsById(long friendId) {
        friendIds.add(friendId);
    }

    public void stopBeingFriendsById(long friendId) {
        friendIds.remove(friendId);
    }

    public boolean hasFriend(long friendId) {
        return friendIds.contains(friendId);
    }
}