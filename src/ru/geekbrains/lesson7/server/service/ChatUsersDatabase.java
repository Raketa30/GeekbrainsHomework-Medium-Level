package ru.geekbrains.lesson7.server.service;

import ru.geekbrains.lesson7.server.entity.User;

import java.util.HashSet;
import java.util.Set;

public class ChatUsersDatabase {
    private final Set<User> userSet = new HashSet<>();

    public ChatUsersDatabase() {
        userSet.add(new User("u1", "p1"));
        userSet.add(new User("u2", "p2"));
        userSet.add(new User("u3", "p3"));
        userSet.add(new User("u4", "p4"));
    }

    public Set<User> getUserSet() {
        return userSet;
    }
}
