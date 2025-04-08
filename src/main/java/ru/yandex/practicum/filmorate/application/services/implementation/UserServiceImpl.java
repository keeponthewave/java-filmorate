package ru.yandex.practicum.filmorate.application.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.application.dto.user.AppUserDto;
import ru.yandex.practicum.filmorate.application.dto.user.AppUserDtoMapper;
import ru.yandex.practicum.filmorate.application.exceptions.DuplicateEmailException;
import ru.yandex.practicum.filmorate.application.repositories.UserRepository;
import ru.yandex.practicum.filmorate.application.exceptions.AlreadyFriendsException;
import ru.yandex.practicum.filmorate.application.services.interfaces.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserDtoMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public List<AppUserDto> getAll() {
        return userRepository.getAll()
                .stream().map(userMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AppUserDto getOne(Long id) {
        return userMapper.fromEntity(userRepository.get(id));
    }

    @Override
    public AppUserDto deleteOne(Long id) {
        return userMapper.fromEntity(userRepository.delete(id));
    }

    @Override
    public AppUserDto createOne(AppUserDto userDto) {
        var user = userMapper.toEntity(userDto);
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        var userByEmail = userRepository.getByEmail(user.getEmail());

        if (userByEmail != null) {
            throw new DuplicateEmailException(userMapper.fromEntity(userByEmail));
        }

        return userMapper.fromEntity(userRepository.create(user));
    }

    @Override
    public AppUserDto updateOne(AppUserDto userDto) {
        var user = userMapper.toEntity(userDto);
        var oldUser = userRepository.get(user.getId());

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            user.setEmail(oldUser.getEmail());
        }

        user.setFriendIds(oldUser.getFriendIds());
        return userMapper.fromEntity(userRepository.update(user));
    }

    @Override
    public void addToFriends(long userId, long friendId) {
        var user = userRepository.get(userId);
        var friend = userRepository.get(friendId);

        if (user.hasFriend(friendId) || friend.hasFriend(userId)) {
            throw new AlreadyFriendsException(user.getId(), friend.getId());
        }

        user.makeFriendsById(friendId);
        friend.makeFriendsById(userId);

        userRepository.update(user);
        userRepository.update(friend);
    }

    @Override
    public void deleteFromFriends(long userId, long friendId) {
        var user = userRepository.get(userId);
        var friend = userRepository.get(friendId);

        if (!(user.hasFriend(friendId) && friend.hasFriend(userId))) {
            return;
        }

        user.stopBeingFriendsById(friendId);
        friend.stopBeingFriendsById(userId);

        userRepository.update(user);
        userRepository.update(friend);
    }

    @Override
    public List<AppUserDto> getFriendsByUserId(long id) {
        return userRepository
                .get(id)
                .getFriendIds()
                .stream()
                .map(userRepository::get)
                .map(userMapper::fromEntity)
                .toList();
    }

    @Override
    public List<AppUserDto> getCommonFriends(long firstUserId, long secondUserId) {
        var firstUser = userRepository.get(firstUserId);
        var secondUser = userRepository.get(secondUserId);

        var commonFriendIds = new HashSet<>(firstUser.getFriendIds());
        commonFriendIds.retainAll(secondUser.getFriendIds());

        return commonFriendIds
                .stream()
                .map(userRepository::get)
                .map(userMapper::fromEntity)
                .toList();
    }
}
