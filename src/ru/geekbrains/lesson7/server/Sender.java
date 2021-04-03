package ru.geekbrains.lesson7.server;

public interface Sender {
    void sendMessage(ClientHandler clientHandler, String nickname, String message);

    void sendPersonalMessage(ClientHandler clientHandler, String nickname, String message);

    void sendStatusMessage(ClientHandler clientHandler, String message);
}
