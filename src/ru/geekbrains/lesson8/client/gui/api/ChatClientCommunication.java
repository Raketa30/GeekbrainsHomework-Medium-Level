package ru.geekbrains.lesson8.client.gui.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientCommunication {
    private DataOutputStream out;
    private DataInputStream in;

    public ChatClientCommunication(String address, int port) {
        try {
            Socket socket = new Socket(address, port);
            System.out.println("Connected to chat server..");
            System.out.println("Please enter auth message: -auth login password");

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            try {
                new Thread(() -> {
                    try {
                        while (true) {
                            String messageFromServer = in.readUTF();
                            System.out.println(messageFromServer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();

                while (true) {
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("quit")) {
                        break;
                    }
                    out.writeUTF(message);
                }
            } catch (IOException e) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
