package ru.geekbrains.lesson7.server;

import ru.geekbrains.lesson7.server.exceptions.ChatServerException;
import ru.geekbrains.lesson7.server.service.AuthService;

import java.io.IOException;

public class MessageTransmitter implements Router, Sender {
    private final AuthService authService = new AuthService();

    @Override
    public void broadcast(ClientHandler clientHandler, String message) {
        for (ClientHandler client : authService.getLoggedUser()) {
            if (!client.equals(clientHandler)) {
                String nickname = clientHandler.getUser().getNickname();
                sendMessage(client, nickname, message);
            }
        }
    }

    @Override
    public void broadcast(String message) {
        for (ClientHandler client : authService.getLoggedUser()) {
            sendStatusMessage(client, message);
        }
    }

    @Override
    public void unicast(ClientHandler clientHandler, String nickname, String message) {
        for (ClientHandler client : authService.getLoggedUser()) {
            if (client.getUser().getNickname().equals(nickname)) {
                String senderNickname = clientHandler.getUser().getNickname();
                sendPersonalMessage(client, senderNickname, message);
                break;
            }
        }
    }

    @Override
    public void sendMessage(ClientHandler clientHandler, String nickname, String message) {
        if (clientHandler.getUser() != null) {
            try {
                clientHandler.getOut()
                        .writeUTF(String.format("%s:> %s", nickname, message));

            } catch (IOException e) {
                throw new ChatServerException("Something went wrong during sending the message.", e);
            }
        }
    }

    @Override
    public void sendStatusMessage(ClientHandler clientHandler, String message) {
        try {
            clientHandler.getOut().writeUTF(message);
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during sending the status message.", e);
        }
    }

    @Override
    public void sendPersonalMessage(ClientHandler clientHandler, String nickname, String message) {
        try {
            clientHandler.getOut().writeUTF(String.format("[Private]%s:> %s", nickname, message));
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during sending the private message.", e);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

}
