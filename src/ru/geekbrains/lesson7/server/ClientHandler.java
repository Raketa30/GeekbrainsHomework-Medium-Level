package ru.geekbrains.lesson7.server;

import ru.geekbrains.lesson7.server.entity.User;
import ru.geekbrains.lesson7.server.exceptions.ChatServerException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler implements Runnable {
    private final ChatServer chatServer;
    private DataOutputStream out;
    private DataInputStream in;
    private User user;
    private Socket socket;

    public ClientHandler(Socket socket, ChatServer chatServer) {
        this.chatServer = chatServer;
        this.socket = socket;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            authentication();

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String message = in.readUTF();
                if (message.equals("-quit")) {
                    chatServer.unscribe(this);
                    break;
                }
                System.out.println(message);
                chatServer.broadcast(this, message);

            } catch (IOException e) {
                throw new ChatServerException("Something went wrong during receiving the message.", e);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(String.format("%s:> %s", user.getNickname(), message));
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during sending the message.", e);
        }
    }

    public void sendMessage(String username, String message) {
        try {
            out.writeUTF(String.format("%s:> %s", username, message));
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during sending the message.", e);
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String authMessage = in.readUTF();

            if (checkCredentials(authMessage)) {
                String[] loginPassword = authMessage.split("\\s+");

                Optional<User> currentUser = chatServer.getAuthService()
                        .findByLoginAndPassword(loginPassword[1], loginPassword[2]);

                if (currentUser.isPresent()) {
                    this.user = currentUser.get();

                    if (!chatServer.isLoggedIn(this)) {
                        System.out.printf("User %s, logged in\n", currentUser.get().getNickname());

                        chatServer.broadcast(String.format("User %s, logged in\n", currentUser.get().getNickname()));
                        chatServer.subscribe(this);

                        sendMessage("Connected to main chat");
                        return;
                    }
                } else {
                    sendMessage("Wrong login or password");
                }
            } else {
                sendMessage("Incorrect authentication request");
            }
        }
    }

    private boolean checkCredentials(String credentials) {
        String reg = "-auth\\s+([a-zA-Z0-9]{2,10})\\s+([a-zA-Z0-9]{2,10})";
        return credentials.matches(reg);
    }

    public User getUser() {
        return this.user;
    }
}
