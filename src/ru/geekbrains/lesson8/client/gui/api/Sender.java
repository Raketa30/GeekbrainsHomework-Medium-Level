package ru.geekbrains.lesson8.client.gui.api;

@FunctionalInterface
public interface Sender {
    void send(String message);
}
