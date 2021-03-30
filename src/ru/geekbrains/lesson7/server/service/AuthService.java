package ru.geekbrains.lesson7.server.service;

import ru.geekbrains.lesson7.server.entity.User;

import java.util.Optional;

public class AuthService {
    ChatUsersDatabase chatUsersDatabase = new ChatUsersDatabase();

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return chatUsersDatabase.getUserSet()
                .stream()
                .filter(user -> user.getLogin().equals(login)
                        && user.getPassword().equals(password))
                .findFirst();

    }
}
