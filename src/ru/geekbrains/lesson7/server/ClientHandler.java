package ru.geekbrains.lesson7.server;

import ru.geekbrains.lesson7.server.exceptions.ChatServerException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final ChatServer chatServer;
    private DataOutputStream out;
    private DataInputStream in;

    public ClientHandler(Socket socket, ChatServer chatServer) {
        this.chatServer = chatServer;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readUTF();
                System.out.println(message);
                chatServer.broadcast(this, message);

            } catch (IOException e) {
                throw new ChatServerException("Something went wrong during receiving the message.", e);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during sending the message.", e);
        }
    }
}
