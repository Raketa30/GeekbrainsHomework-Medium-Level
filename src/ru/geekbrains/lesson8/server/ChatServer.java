package ru.geekbrains.lesson8.server;

import ru.geekbrains.lesson8.server.exceptions.ChatServerException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    private final MessageTransmitter messageTransmitter = new MessageTransmitter();

    public ChatServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Connection to server..");

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    messageTransmitter.broadcast(String.format("server:> %s", message));
                }
            }).start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, messageTransmitter);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            throw new ChatServerException("Something wrong with server", e);
        }
    }
}
