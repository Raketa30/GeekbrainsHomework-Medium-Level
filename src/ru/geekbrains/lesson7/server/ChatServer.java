package ru.geekbrains.lesson7.server;

import ru.geekbrains.lesson7.server.service.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChatServer {
    private final AuthService authService = new AuthService();
    private final Set<ClientHandler> clientHandlers = new HashSet<>();

    public ChatServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Connection to server..");
            serverMessageSender();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, this);

                new Thread(handler).start();
                clientHandlers.add(handler);
                System.out.println("Client connected");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

    public void broadcast(ClientHandler current, String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.equals(current)) {
                clientHandler.sendMessage(message);
            }
        }
    }

    public void broadcast(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(message);
        }
    }

    public void serverMessageSender() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String message = scanner.nextLine();
                broadcast(String.format("Server:>%s", message));
            }
        }).start();
    }
}
