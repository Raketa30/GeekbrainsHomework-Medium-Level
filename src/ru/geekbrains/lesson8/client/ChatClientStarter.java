package ru.geekbrains.lesson8.client;

public class ChatClientStarter {
    public static void run() {
        run("localhost", 8000);
    }

    public static void run(String host, int port) {
        new Chat(host, port);
    }
}
