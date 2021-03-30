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
    private final Set<ClientHandler> loggedOn = new HashSet<>();

    public ChatServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Connection to server..");
            serverMessageSender();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, this);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

    public void broadcast(ClientHandler current, String message) {
        for (ClientHandler clientHandler : loggedOn) {
            if (!clientHandler.equals(current)) {
                clientHandler.sendMessage(message);
            }
        }
    }

    public void broadcast(String message) {
        for (ClientHandler clientHandler : loggedOn) {
            clientHandler.sendMessage(message);
        }
    }

    public void serverMessageSender() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                broadcast(message);
            }
        }).start();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void subscribe(ClientHandler handler) {
        loggedOn.add(handler);
    }

    public void unscribe(ClientHandler handler) {
        loggedOn.remove(handler);
    }

    public boolean isLoggedIn(ClientHandler handler) {
        return loggedOn.stream()
                .anyMatch(client -> client.getUser().equals(handler.getUser()));
    }
}
