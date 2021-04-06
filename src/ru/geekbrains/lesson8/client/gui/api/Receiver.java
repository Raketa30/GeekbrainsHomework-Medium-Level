package ru.geekbrains.lesson8.client.gui.api;

@FunctionalInterface
public interface Receiver {
    void receive(String data) throws InterruptedException;
}
