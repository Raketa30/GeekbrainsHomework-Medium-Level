package ru.geekbrains.lesson8.client;

import ru.geekbrains.lesson8.client.gui.ChatFrame;
import ru.geekbrains.lesson8.client.gui.api.ChatClientCommunication;

public class Chat {
    private final ChatFrame frame;
    private final ChatClientCommunication communication;

    public Chat(String host, int port) {
        frame = new ChatFrame();
        communication = new ChatClientCommunication(host, port);
    }


}
