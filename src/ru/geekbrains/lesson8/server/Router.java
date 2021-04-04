package ru.geekbrains.lesson8.server;

public interface Router {
    void broadcast(String message);

    void broadcast(ClientHandler clientHandler, String message);

    void unicast(ClientHandler clientHandler, String nickname, String message);
}
