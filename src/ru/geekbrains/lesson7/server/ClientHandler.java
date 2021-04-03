package ru.geekbrains.lesson7.server;

import ru.geekbrains.lesson7.server.entity.User;
import ru.geekbrains.lesson7.server.exceptions.ChatServerException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 1. Разобраться с кодом
 * 2. * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет»,
 * то только клиенту с ником nick3 должно прийти сообщение «Привет»
 */

public class ClientHandler implements Runnable {
    private DataOutputStream out;
    private DataInputStream in;
    private User user;
    private MessageTransmitter messageTransmitter;

    public ClientHandler(Socket socket, MessageTransmitter messageTransmitter) {
        this.messageTransmitter = messageTransmitter;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            authentication();
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong with ClientHandler");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("/w ")) {
                    if (checkUser(message)) {
                        String nickname = takeUserNicknameFromMessage(message);
                        String formedMessage = formPersonalMessage(message);

                        messageTransmitter.unicast(this, nickname, formedMessage);

                    } else {
                        messageTransmitter.sendStatusMessage(this, "Current user not logged on");
                    }

                } else if (message.equals("-quit")) {
                    messageTransmitter.getAuthService().unscribe(this);
                    break;

                } else {
                    System.out.println(message);
                    messageTransmitter.broadcast(this, message);
                }


            } catch (IOException e) {
                messageTransmitter.getAuthService().unscribe(this);
                throw new ChatServerException("Something went wrong during receiving the message.", e);
            }
        }
    }

    public MessageTransmitter getMessageTransmitter() {
        return messageTransmitter;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void authentication() throws IOException {
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            String authMessage = in.readUTF();
            isLoggedIn = messageTransmitter.getAuthService()
                    .authentication(this, authMessage);
        }
    }

    private boolean checkUser(String message) {
        String[] arr = message.split("\\s+");
        return messageTransmitter.getAuthService().checkLoggedUserByNickname(arr[1]);
    }

    private String takeUserNicknameFromMessage(String message) {
        String[] mess = message.split("\\s+");
        return mess[1];
    }

    private String formPersonalMessage(String message) {
        String[] mess = message.split("\\s+");
        StringBuilder builder = new StringBuilder();

        for (int i = 2; i < mess.length; i++) {
            builder.append(mess[i]).append(" ");
        }
        return builder.toString();

    }


}
